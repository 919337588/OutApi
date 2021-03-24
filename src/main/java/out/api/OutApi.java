package out.api;

import out.api.util.ClassHelper;
import out.api.util.ObjMapBeanUtil;
import out.api.util.YouDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutApi {
    private static String bao = "cn.deal";



    public static List<ControlMethon> outapi(String bao) {
        OutApi.bao=bao;
        return ClassHelper.getClzFromPkg(bao).stream().filter(
                v -> v.getAnnotation(RestController.class) != null || v.getAnnotation(Controller.class) != null
        ).flatMap(v ->
                {
                    Stream<Method> stream = Arrays.stream(v.getMethods());
                    RequestMapping annotation = v.getAnnotation(RequestMapping.class);
                    String path = annotation != null ? (annotation.value() != null && annotation.value().length > 0 ? annotation.value()[0] : annotation.name()) : "";
                    return stream.map(j -> new Object[]{path, j});
                }
        ).filter(
                v ->
                        ((Method) v[1]).getAnnotation(PostMapping.class) != null ||
                                ((Method) v[1]).getAnnotation(GetMapping.class) != null ||
                                ((Method) v[1]).getAnnotation(PutMapping.class) != null ||
                                ((Method) v[1]).getAnnotation(DeleteMapping.class) != null ||
                                ((Method) v[1]).getAnnotation(RequestMapping.class) != null

        ).map(v -> {
            try {
                return getRequestParameter((Method) v[1], (String) v[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).filter(v -> v != null).collect(Collectors.toList());
    }

    public static ControlMethon getRequestParameter(Method method, String path) throws Exception {
        ControlMethon controlMethon = new ControlMethon();
        try {
            String[][] inFOAll = getInFO(method);
            if (inFOAll == null) {
                return null;
            }
            String[] inFO=inFOAll[0];
            controlMethon.type = inFO[0];
            controlMethon.consumes=inFOAll[1];
            if (path.endsWith("/") && inFO[1].startsWith("/")) {
                controlMethon.path = path + inFO[1].substring(1);
            } else if (!path.endsWith("/") && !inFO[1].startsWith("/")) {
                controlMethon.path = path + "/" + inFO[1];
            } else {
                controlMethon.path = path + inFO[1];
            }
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                controlMethon.name = apiOperation.value();
            } else {
                controlMethon.name = YouDao.execCurl(method.getName());
            }
            ApiImplicitParams apiImplicitParams = method.getAnnotation(ApiImplicitParams.class);
            Map<String, String> apiImplicitParamMap = new HashMap<>();
            if (apiImplicitParams != null) {
                ApiImplicitParam[] value = apiImplicitParams.value();
                Arrays.stream(value).forEach(v -> apiImplicitParamMap.put(v.name(), v.value()));
            }
            List<HashMap> from = new LinkedList<>();
            List<HashMap> pathVal = new LinkedList<>();
            Parameter[] parameters = method.getParameters();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
                if (requestBody != null) {
                    Object obj = parseRequertbody(parameterTypes[i], genericParameterTypes[i]);
                    controlMethon.requestBody = obj;
                } else {
                    RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
                    PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
                    if (requestParam != null) {
                        String name = requestParam.name();
                        name = name == null || "".equals(name.trim()) ? requestParam.value() : name;
                        name = name == null || "".equals(name.trim()) ? parameters[i].getName() : name;
                        String nameC = apiImplicitParamMap.get(name);
                        nameC = nameC == null || "".equals(nameC.trim()) ? YouDao.execCurl(name) : nameC;
                        name = name + "~~" + nameC;
                        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                        objectObjectHashMap.put(name, parameterTypes[i].getName());
                        from.add(objectObjectHashMap);
                    } else if (pathVariable != null) {
                        String name = pathVariable.name();
                        name = name == null || "".equals(name.trim()) ? pathVariable.value() : name;
                        name = name == null || "".equals(name.trim()) ? parameters[i].getName() : name;
                        String nameC = apiImplicitParamMap.get(name);
                        nameC = nameC == null || "".equals(nameC.trim()) ? YouDao.execCurl(name) : nameC;
                        name = name + "~~" + nameC;
                        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                        objectObjectHashMap.put(name, parameterTypes[i].getName());
                        pathVal.add(objectObjectHashMap);
                    } else if (parameterTypes[i].getName().indexOf(bao) > -1) {
                        Set<Field> allFieldsList = ObjMapBeanUtil.getAllFields(parameterTypes[i]);
                        for (Field o1 : allFieldsList) {
                            ApiModelProperty[] annotationsByType = o1.getAnnotationsByType(ApiModelProperty.class);
                            String nameC = null;
                            if (annotationsByType.length > 0) {
                                nameC = annotationsByType[0].name();
                                nameC = nameC == null || "".equals(nameC.trim()) ? annotationsByType[0].value() : nameC;
                            }
                            nameC = nameC == null || "".equals(nameC.trim()) ? apiImplicitParamMap.get(o1.getName()) : nameC;
                            nameC = nameC == null || "".equals(nameC.trim()) ? YouDao.execCurl(o1.getName()) : nameC;
                            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                            objectObjectHashMap.put(o1.getName() + "~~" + nameC, parameterTypes[i].getName());
                            from.add(objectObjectHashMap);
                        }
                    }
                }
            }
            Class<?> returnType = method.getReturnType();
            Type genericReturnType = method.getGenericReturnType();
            if (returnType != null && returnType != Void.TYPE) {
                controlMethon.returnJson = parseRequertbody(returnType, genericReturnType);
            }
            controlMethon.requestParam = from;
            controlMethon.pathVariable = pathVal;
            return controlMethon;
        } finally {
        }
    }

    static HashMap<Type, Object> parseMap = new HashMap<>();

    public static Object parseRequertbody(Class type, Type type1) throws Exception {
        Object o = parseMap.get(type1);
        if (o != null) {
            return type1.getTypeName();
        }
        if (type.isInstance(new HashMap<>())) {
            o = new HashMap<>();
            parseMap.put(type1, o);
        } else if (type.getName().indexOf(bao) > -1) {
            o = new HashMap<>();
            parseMap.put(type1, o);
            Set<Field> allFieldsList = ObjMapBeanUtil.getAllFields(type);
            for (Field o1 : allFieldsList) {
                ApiModelProperty[] annotationsByType = o1.getAnnotationsByType(ApiModelProperty.class);
                String nameC = null;
                if (annotationsByType.length > 0) {
                    nameC = annotationsByType[0].name();
                    nameC = nameC == null || "".equals(nameC.trim()) ? annotationsByType[0].value() : nameC;
                }
                nameC = nameC == null || "".equals(nameC.trim()) ? YouDao.execCurl(o1.getName()) : nameC;
                ((Map) o).put(o1.getName() + "~~" + nameC, parseRequertbody(o1.getType(), o1.getGenericType()));
            }
        } else if (type.isInstance(new ArrayList<>()) || type.isInstance(new HashSet<>())) {
            o = new LinkedList<>();
            parseMap.put(type1, o);
            if (type1 != null) {
                if (type1 instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) type1).getActualTypeArguments();
                    for (Type type2 : actualTypeArguments) {
                       try {
                           ((List) o).add(parseRequertbody(Class.forName(type2.getTypeName()), type2));
                       }catch (Exception e){
                           ((List) o).add(type2.getTypeName());
                       }
                    }
                }
            }
        } else {

            return type.getName();

        }

        return o;
    }


    public static String[][] getInFO(Method method) throws Exception {
        String path = "", type = "";
        String[] cus=new String[0];
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (postMapping != null) {
            path = getPath(postMapping);
            type = "POST";
            cus=postMapping.consumes();
        } else if (putMapping != null) {
            path = getPath(putMapping);
            type = "PUT";
            cus=putMapping.consumes();
        } else if (getMapping != null) {
            path = getPath(getMapping);
            type = "GET";
            cus=getMapping.consumes();
        } else if (deleteMapping != null) {
            path = getPath(deleteMapping);
            type = "DELETE";
            cus=deleteMapping.consumes();
        } else if (requestMapping != null) {
            path = getPath(requestMapping);
            cus=requestMapping.consumes();
            RequestMethod[] method1 = requestMapping.method();
            if (method1 == null || method1.length == 0) {
                type = "ALL";
            } else {
                for (RequestMethod requestMethod : method1) {
                    type = "," + requestMethod.name();
                }
                type = type.substring(1);
            }
        } else {
            return null;
        }

        return new String[][]{{type, path},cus};
    }

    public static String getPath(Object obj) throws Exception {
        Method name = obj.getClass().getMethod("name");
        Method value = obj.getClass().getMethod("value");
        name.setAccessible(true);
        value.setAccessible(true);
        Object o = name.invoke(obj);
        return o == null || "".equals(o.toString().trim()) ? ((String[]) value.invoke(obj))[0] : o.toString();
    }

    public static class ControlMethon {
        public String type;
        public String name;
        public String path;
        public List<HashMap> requestParam;
        public List<HashMap> pathVariable;
        public Object requestBody;
        public Object returnJson;
        public String[] consumes;

    }
}
