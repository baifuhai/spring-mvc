package com.test.helloworld.controller;

import com.test.helloworld.entity.Car;
import com.test.helloworld.entity.Person;
import com.test.helloworld.exception.MyException;
import com.test.helloworld.view.HelloView;
import com.test.helloworld.view.MyExcelView;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//@SessionAttributes(value = {"car"}, types = {String.class})
//@RequestMapping("test")
@Controller
public class TestController {

    /**
     * 1. 使用 @RequestMapping 注解来映射请求的 URL
     *
     * 2. 返回值会通过视图解析器解析为实际的物理视图，对于 InternalResourceViewResolver 视图解析器，会做如下的解析:
     *      通过 prefix + returnVal + suffix 这样的方式得到实际的物理视图/WEB-INF/views/success.jsp，然会做转发操作
     *
     * 3. @RequestMapping 除了修饰方法, 还可来修饰类
     *      类处: 提供初步的请求映射信息，相对于 WEB 应用的根目录
     *      方法处: 提供进一步的细分映射信息，相对于类定义处的 URL。若类定义处未标注 @RequestMapping，则方法处标记的 URL 相对于 WEB 应用的根目录
     *
     * value：请求url，value = {"hello", "xxx"}
     * method：请求方法，method = RequestMethod.GET
     * params：请求参数，params = {"id", "!name", "age != 10"}，必须包含id参数，必须不包含name参数，必须包含age参数且不等于10
     * headers：请求头，headers = {"Accept-Language=en-US,zh;q=0.9"}
     *
     */
    @RequestMapping(value = {"testRequestMapping", "testRequestMapping2"})
    public String testRequestMapping() {
        return "success";
    }

    @RequestMapping(value = "testRequestMappingMethod", method = RequestMethod.GET)
    public String testRequestMappingMethod() {
        return "success";
    }

    @RequestMapping(value = "testRequestMappingParams", params = {"id", "!name", "age != 10"})
    public String testRequestMappingParams() {
        return "success";
    }

    @RequestMapping(value = "testRequestMappingHeaders", headers = {"Accept-Language=zh-CN,zh;q=0.9"})
    public String testRequestMappingHeaders() {
        return "success";
    }

    /**
     * url支持Ant风格通配符：
     *  ?  匹配任何1个字符，/user/? 匹配 /user/a
     *  *  匹配0个或多个字符，/user/* 匹配 /user/ 或 /user/a 或 /user/aa
     *  ** 匹配多层路径，/user/** 匹配 /user/ 或 /user/a 或 /user/a/b
     */
    @RequestMapping("testAnt/?")
    public String testAnt() {
        return "success";
    }

    @RequestMapping("testAnt2/*")
    public String testAnt2() {
        return "success";
    }

    @RequestMapping("testAnt3/**/abc")
    public String testAnt3() {
        return "success";
    }

    /**
     * @PathVariable 可以来映射 URL 中的占位符到目标方法的参数中
     */
    @RequestMapping("testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("id: " + id);
        return "success";
    }

    /**
     * REST
     *
     * 获取 user/1 GET
     * 新增 user   POST
     * 修改 user/1 PUT
     * 删除 user/1 DELETE
     *
     * 浏览器 form 表单只支持 GET 与 POST 请求，而 PUT 和 DELETE 等 method 并不支持
     * Spring3.0 添加了一个过滤器 HiddenHttpMethodFilter，可以把 POST 请求转为 PUT 或 DELETE 请求
     * 需要携带一个 name = "_method" 的隐藏域，值为 PUT 或 DELETE，不区分大小写
     *
     */
    @RequestMapping(value = "testRestGet/{id}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable("id") Integer id) {
        System.out.println("testRestGet, id: " + id);
        return "success";
    }

    @RequestMapping(value = "testRestPost", method = RequestMethod.POST)
    public String testRestPost(String name) {
        System.out.println("testRestPost, name: " + name);
        return "success";
    }

    @RequestMapping(value = "testRestPut/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable("id") Integer id, String name) {
        System.out.println("testRestPut, id: " + id + ", name: " + name);
        return "success";
    }

    @RequestMapping(value = "testRestDelete/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable("id") Integer id) {
        System.out.println("testRestDelete, id: " + id);
        return "success";
    }

    /**
     * @RequestParam 来映射请求参数
     * value 请求参数的参数名
     * required 参数是否必须，默认为 true
     * defaultValue 请求参数的默认值
     */
    @RequestMapping("testRequestParam")
    public String testRequestParam(
            @RequestParam(value = "username") String un,
            @RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
        System.out.println("testRequestParam, username: " + un + ", age: " + age);
        return "success";
    }

    /**
     * 了解: 映射请求头信息，用法同 @RequestParam
     */
    @RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader("Accept-Language") String al) {
        System.out.println("testRequestHeader, Accept-Language: " + al);
        return "success";
    }

    /**
     * 了解: @CookieValue 映射一个 Cookie 值，属性同 @RequestParam
     */
    @RequestMapping("testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("testCookieValue, sessionId: " + sessionId);
        return "success";
    }

    /**
     * Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值，支持级联属性
     */
    @RequestMapping("/testPojo")
    public String testPojo(Person person) {
        System.out.println("testPojo, person: " + person);
        return "success";
    }

    /**
     * 可以使用 Servlet 原生的 API 作为目标方法的参数，具体支持以下类型
     *
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale
     * InputStream
     * OutputStream
     * Reader
     * Writer
     *
     * 参考 AnnotationMethodHandlerAdapter.resolveStandardArgument
     */
    @RequestMapping("testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response, InputStream in, Writer out, String id) throws IOException {
        String s = IOUtils.toString(in, "UTF-8");
        System.out.println("testServletAPI, s: " + s);
        System.out.println("testServletAPI, id: " + id);
        System.out.println("testServletAPI, " + request + ", " + response);
        out.write("hello spring-mvc");
    }

    /**
     * 目标方法的返回值可以是 ModelAndView 类型
     * 其中可以包含视图和模型信息
     * SpringMVC 会把 ModelAndView 的 model 中数据放入到 request 域中
     */
    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.setViewName("success");

        //添加模型数据到 ModelAndView 中
        modelAndView.addObject("msg", "hello");

        return modelAndView;
    }

    /**
     * 目标方法可以添加 Map 类型（实际上也可以是 Model 类型或 ModelMap 类型）的参数
     */
    @RequestMapping("testMap")
    public String testMap(Map<String, Object> map){
        System.out.println(map.getClass());//org.springframework.validation.support.BindingAwareModelMap
        map.put("msg", Arrays.asList("Tom", "Jerry", "Mike"));
        return "success";
    }

    /**
     * @SessionAttributes
     * 可以通过属性名指定需要放到会话中的属性(value 属性值)
     * 还可以通过属性的对象类型指定哪些模型属性需要放到会话中(types 属性值)
     *
     * 注意: 该注解只能放在类的上面
     */
    @RequestMapping("testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map){
        map.put("car", new Car("benz"));
        map.put("msg", "hello");
        return "success";
    }

    /**
     * 运行流程:
     *
     * SpringMVC 确定目标方法 POJO 类型入参的过程
     * 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象，把对象放到 Map 中
     * 1. 确定一个 key:
     *   1). 若目标方法的 POJO 类型的参数没有使用 @ModelAttribute 作为修饰，则 key 为 POJO 类名首字母小写
     *   2). 若使用了 @ModelAttribute 来修饰，则 key 为 @ModelAttribute 注解的 value 属性值
     * 2. 若 implicitModel 中存在 key 对应的对象，则把表单的请求参数赋给该对象的对应属性，再把该对象传入目标方法的参数
     * 3. 若 implicitModel 中不存在 key 对应的对象
     *      则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰
     *          若使用了该注解，且 @SessionAttributes 注解的 value 属性值中包含了 key，则会从 HttpSession 中来获取 key
     *              对应的 value 值，若存在则表单参数赋值后传入到目标方法的入参中，若不存在则将抛出异常
     *          若没有使用注解，或 @SessionAttributes 注解的 value 值中不包含 key，则会通过反射来创建 POJO 类型的参数
     *              表单参数赋值后传入为目标方法的参数，再把 key 和 对象保存到 implicitModel 中
     *
     * 源代码分析的流程
     * 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
     * 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
     *   1). 创建 WebDataBinder 对象:
     *     ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写
     *     注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute 的 value 属性值
     *     ②. 确定 target 属性:
     *     > 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
     * 	   > 若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
     *     获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常
     *     > 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
     *     和 attrName 相匹配, 则通过反射创建了 POJO 对象
     *   2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性
     *   3). SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel 近而传到 request 域对象中
     *   4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参
     */
    @RequestMapping("testModelAttribute")
    public String testModelAttribute(@ModelAttribute("car2") Car car){
        System.out.println("testModelAttribute, car: " + car);
        return "success";
    }

    /**
     * 有 @ModelAttribute 标记的方法，会在每个目标方法执行之前被调用
     */
    //@ModelAttribute
    public void getUser(Integer id, Map<String, Object> map){
        System.out.println("modelAttribute method");
        if (id != null) {
            //模拟从数据库中获取对象
            Car car = new Car("bmw");
            System.out.println("从数据库中获取一个对象: " + car);

            map.put("car2", car);
        }
    }

    /**
     * testViewAndViewResolver
     */
    @RequestMapping("testViewAndViewResolver")
    public String testViewAndViewResolver(){
        return "success";
    }

    @RequestMapping("testViewAndViewResolver2")
    public View testViewAndViewResolver2(){
        return new InternalResourceView("success");//不行
    }

    /**
     * testView
     */
    @RequestMapping("testHelloView1")
    public String testHelloView1(Map<String, Object> map){
        map.put("name", "tom");
        return "helloView";
    }

    @RequestMapping("testHelloView2")
    public View testHelloView2(Map<String, Object> map){
        map.put("name", "tom");
        return new HelloView();
    }

    @RequestMapping("testHelloView3")
    public ModelAndView testHelloView3(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tom");
        ModelAndView mv = new ModelAndView("helloView", map);
        //ModelAndView mv = new ModelAndView(new HelloView(), map);
        return mv;
    }

    @RequestMapping("testHelloView4")
    public ModelAndView testHelloView4(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("helloView");
        //mv.setView(new HelloView());
        mv.addObject("name", "tom");
        return mv;
    }

    /**
     * testExcelView
     */
    @RequestMapping("testExcelView")
    public String testExcelView(Map<String, Object> map){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "a"));
        personList.add(new Person(2, "b"));

        map.put("fileName", "personList");
        map.put("personList", personList);

        return "myExcelView";
    }

    @RequestMapping("testExcelView2")
    public View testExcelView2(Map<String, Object> map){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "a"));
        personList.add(new Person(2, "b"));

        map.put("fileName", "personList");
        map.put("personList", personList);

        return new MyExcelView();
    }

    /**
     * testRedirect
     */
    @RequestMapping("testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/";
    }

    /**
     * 由 @InitBinder 标识的方法，可以对 WebDataBinder 对象进行初始化
     * WebDataBinder 是 DataBinder 的子类
     * 用于完成由表单字段到 JavaBean 属性的绑定
     *   1. @InitBinder方法不能有返回值，它必须声明为void
     *   2. @InitBinder方法的参数通常是是 WebDataBinder
     *
     * DataBinder 类里有conversionService、validators、bindingResult
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //binder.setDisallowedFields("birth");//该参数不会赋值给对象
    }

    /**
     * 类型转换器
     *
     * Spring MVC 上下文中内建了很多转换器，可完成大多数 Java 类型的转换工作
     *   java.lang.String -> java.lang.Boolean : StringToBooleanConverter@1ca6626
     *   java.lang.String -> java.lang.Character : StringToCharacterConverter@1143800
     *   java.lang.String -> java.lang.Enum : StringToEnumConverterFactory@1bba86e
     *   java.lang.String -> java.lang.Number : StringToNumberConverterFactory@18d2c12
     *   java.lang.String -> java.util.Locale : StringToLocaleConverter@3598e1
     *   java.lang.String -> java.util.Properties : StringToPropertiesConverter@c90828
     *   java.lang.String -> java.util.UUID
     *
     * Spring MVC 定义了 3 种类型的转换器接口
     *   Converter<S,T>
     *   ConverterFactory<S, R>
     *   GenericConverter
     * 实现任意一个转换器接口都可以作为自定义转换器注册到 ConversionServiceFactoryBean 中
     */
    @RequestMapping("testConverter")
    public String testConverter(Person person) {
        System.out.println("testConverter, person: " + person);
        return "success";
    }

    /**
     * 格式化器
     */
    @RequestMapping("testFormatter")
    public String testFormatter(Person person, BindingResult bindingResult) {
        System.out.println("testFormatter, person: " + person);
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult error");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
        }
        return "success";
    }

    /**
     * JSR 303
     * JSR 303 是 Java 为 Bean 数据合法性校验提供的标准框架，它已经包含在 JavaEE 6.0 中
     * 通过在 Bean 属性上标注类似于 @NotNull、@Max 等标准的注解指定校验规则，并通过标准的验证接口对 Bean进行验证
     *
     * Hibernate Validator 扩展注解
     * Hibernate Validator 是 JSR 303 的一个参考实现，除支持所有标准的校验注解外，它还支持以下的扩展注解
     *   - @Email
     *   - @Length
     *   - @NotEmpty
     *   - @Range
     *
     * Spring 本身并没有提供 JSR303 的实现，所以必须将JSR303 的实现者的 jar 包放到类路径下，如 hibernate-validator
     *
     * Spring 的 LocalValidatorFactoryBean 既实现了 Spring 的 Validator 接口，也实现了 JSR 303 的 Validator 接口
     * 只要在 Spring 容器中定义了一个 LocalValidatorFactoryBean，即可将其注入到需要数据校验的 Bean 中
     * <mvc:annotation-driven/> 会默认装配好一个 LocalValidatorFactoryBean
     *
     * 通过在处理方法的入参上标注 @Valid 注解即可让 Spring MVC 在完成数据绑定后执行数据校验的工作
     * 校验结果保存到 BindingResult 或 Errors 类型的入参中，这两个类都位于 org.springframework.validation 包中
     *
     * 需校验的 Bean 对象和其绑定结果对象或错误对象是成对出现的，它们之间不允许声明其他的入参
     *
     * JSP 页面上可通过 <form:errors path="*"> <form:errors path="username"> 显示错误消息
     *
     * 每个属性在数据绑定和数据校验发生错误时，都会生成一个对应的 FieldError 对象
     * 当一个属性校验失败后，校验框架会为该属性生成 4 个消息代码，这些代码以校验注解类名为前缀
     * 结合 modleAttribute、属性名及属性类型名生成多个对应的消息代码
     * 例如 User 类中的 password 属性标准了一个 @Pattern 注解，当该属性值不满足 @Pattern 所定义的规则时
     * 就会产生以下4个错误代码：
     *   Pattern.user.password
     *   Pattern.password
     *   Pattern.java.lang.String
     *   Pattern
     * 当使用 Spring MVC 标签显示错误消息时，Spring MVC 会查看 WEB 上下文是否装配了对应的国际化消息
     * 如果没有，则显示默认的错误消息，否则使用国际化消息
     */
    @RequestMapping("testJSR303")
    public String testJSR303(@Valid Person person, BindingResult bindingResult) {
        System.out.println("testJSR303, person: " + person);
        //bindingResult.getClass() -> BeanPropertyBindingResult
        if (bindingResult.hasErrors() || bindingResult.getErrorCount() > 0) {
            System.out.println("bindingResult error");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
            return "jsr303";//重定向页面上不能显示错误信息
        }
        return "redirect:/testJSR303Page";
    }

    @RequestMapping("testJSR303Page")
    public String testJSR303Page(Map<String, Object> map) {
        map.put("person", new Person());
        return "jsr303";
    }

    @ResponseBody
    @RequestMapping("testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("testRequestBody, body: " + body);
        return "hello";
    }

    /**
     * 将请求报文转换成 HttpInputMessage 对象
     * 再由 HttpMessageConverter 调用 T read(Class<? extends T> clazz, HttpInputMessage inputMessage) 方法
     * 将 HttpInputMessage 调用 InputStream getBody() 方法，将 InputStream 转换成 Java 对象
     *
     * 由 HttpMessageConverter 将 Java 对象转换成 HttpOutputMessage 对象
     * 再调用 HttpOutputMessage 的 OutputStream getBody() 方法，将 OutputStream 写到 response
     *
     * MappingJackson2HttpMessageConverter
     */
    @ResponseBody
    @RequestMapping("testResponseBody")
    public List<Person> testResponseBody() {
        return Arrays.asList(new Person(1, "a"), new Person(2, "b"));
    }

    /**
     * StringHttpMessageConverter
     */
    @ResponseBody
    @RequestMapping("testResponseBodyString")
    public String testResponseBodyString() {
        return "{\"id\":1}";
    }

    /**
     * ByteArrayHttpMessageConverter
     */
    @ResponseBody
    @RequestMapping("testResponseBodyByteArray")
    public byte[] testResponseBodyByteArray(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=spring-mvc.xml");

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("spring-mvc.xml");
        byte[] bytes = IOUtils.toByteArray(in);

        //InputStream in = new ClassPathResource("spring-mvc.xml").getInputStream();
        //byte[] bytes = FileCopyUtils.copyToByteArray(in);

        return bytes;
    }

    /**
     * 不用加@ResponseBody
     */
    @RequestMapping("testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpServletRequest request) throws IOException {
        InputStream in = request.getServletContext().getResourceAsStream("/file/abc.txt");
        byte[] body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=abc.txt");

        HttpStatus status = HttpStatus.OK;

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body, headers, status);

        return responseEntity;
    }

    @Autowired
    ResourceBundleMessageSource messageSource;

    @RequestMapping("testI18n")
    public String testI18n(HttpServletRequest request, Locale locale) {
        //Locale locale = request.getLocale();
        System.out.println(messageSource.getMessage("key.username", null, locale));
        System.out.println(messageSource.getMessage("key.password", null, locale));
        return "success";
    }

    @RequestMapping("testFileUpload")
    public String testFileUpload(String desc, @RequestParam("file") MultipartFile multipartFile, @RequestParam("file2") MultipartFile multipartFile2) throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("name: " + multipartFile.getName());
        System.out.println("originalFilename: " + multipartFile.getOriginalFilename());
        System.out.println("contentType: " + multipartFile.getContentType());//application/octet-stream
        System.out.println("size: " + multipartFile.getSize());

        InputStream in = multipartFile.getInputStream();
        FileOutputStream out = new FileOutputStream(new File("d:/" + multipartFile.getOriginalFilename()));

        IOUtils.copy(in, out);
        //FileCopyUtils.copy(in, out);

        //不flush不close和
        //只flush不close一样，都不可以，会产生文件，但是文件大小显示为0，打开文件或右键查看属性时文件大小有了，但不能删除
        //不flush只close可以，大小显示正确，可以删除
        out.flush();
        out.close();

        multipartFile2.transferTo(new File("d:/" + multipartFile2.getOriginalFilename()));

        return "success";
    }

    @RequestMapping("testFileUpload2")
    public String testFileUpload2(String desc, MultipartHttpServletRequest request) throws IOException {
        System.out.println("desc: " + desc);

        MultipartFile multipartFile = request.getFile("file");
        MultipartFile multipartFile2 = request.getFile("file2");

        multipartFile.transferTo(new File("d:/" + multipartFile.getOriginalFilename()));
        multipartFile2.transferTo(new File("d:/" + multipartFile2.getOriginalFilename()));

        return "success";
    }

    /**
     * 先 preHandle 正序执行（可以考虑做权限，日志等）
     * 再 执行目标方法
     * 再 postHandle 逆序执行（可以对请求域中的属性或视图做修改）
     * 再 渲染视图
     * 再 afterCompletion 逆序执行（释放资源）
     *
     * 如果所有的拦截器 preHandle 都返回 true，则按上述执行
     * 如果第3个拦截器 preHandle 返回 false，则后续的 preHandle 不执行，目标方法不执行，postHandle 都不执行，afterCompletion 从第2个开始逆序执行
     */
    @RequestMapping("testInterceptor")
    public String testInterceptor() {
        return "success";
    }

    /**
     * HandlerExceptionResolver 接口
     *   ExceptionHandlerExceptionResolver（要有<mvc:annotation-driven/>）
     *   ResponseStatusExceptionResolver
     *   DefaultHandlerExceptionResolver
     *   SimpleMappingExceptionResolver
     *
     */

    /**
     * ExceptionHandlerExceptionResolver
     */
    @RequestMapping("testExceptionHandler")
    public String testExceptionHandler(int i) {
        System.out.println(1 / i);
        return "success";
    }

    /**
     * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数，该参数对应发生的异常对象
     * 2. @ExceptionHandler 方法的入参中不能传入 Map，若希望把异常信息传到页面上，需要使用 ModelAndView 作为返回值
     * 3. @ExceptionHandler 方法标记的异常有优先级的问题，精确匹配优先
     * 4. @ControllerAdvice 如果在当前 Controller 中找不到 @ExceptionHandler 方法处理当前方法出现的异常
     *      则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常
     */
    /*@ExceptionHandler(value = {ArithmeticException.class})
    public ModelAndView exceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("exception", e);
        return mv;
    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView exceptionHandler2(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("exception", e);
        return mv;
    }*/

    /**
     * ResponseStatusExceptionResolver
     *
     * 在异常及异常父类中找到 @ResponseStatus 注解，然后使用这个注解的属性进行处理
     * 定义一个 @ResponseStatus 注解修饰的异常类，若在处理器方法中抛出了该异常
     * 若 ExceptionHandlerExceptionResolver 不能处理该异常，由于该异常带有 @ResponseStatus注解
     * 因此会被ResponseStatusExceptionResolver 处理，最后响应注解里设置的响应码和信息
     *
     */
    @RequestMapping("testResponseStatus")
    public String testResponseStatus(int i) {
        if (i == 0) {
            throw new MyException();
        }
        return "success";
    }

    /**
     * 也可以标在方法上，正常返回就会以该注解设置的响应码和信息返回给客户端
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "[MyException]...")
    @RequestMapping("testResponseStatus2")
    public String testResponseStatus2(int i) {
        if (i == 0) {
            throw new MyException();
        }
        return "success";
    }

    /**
     * DefaultHandlerExceptionResolver
     *
     * 对一些特殊的异常进行处理，比如
     * NoSuchRequestHandlingMethodException
     * HttpRequestMethodNotSupportedException
     * HttpMediaTypeNotSupportedException
     * HttpMediaTypeNotAcceptableException等
     */
    @RequestMapping(value = "testDefaultHandlerExceptionResolver", method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver() {
        return "success";
    }

    /**
     * SimpleMappingExceptionResolver
     *
     * 如果希望对所有异常进行统一处理，可以使用SimpleMappingExceptionResolver
     * 它将异常类名映射为视图名，即发生异常时使用对应的视图报告异常
     */
    @RequestMapping("testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(int i) {
        int[] arr = new int[1];
        System.out.println(arr[i]);
        return "success";
    }

}

