/**
 * 1.MVC设计思想
 *  软件架构的思想，将一个软件按照模型、视图、控制器进行划分.
 *  控制器（Controller）：用来控制程序的流转
 *  模型:用来封装数据和处理业务逻辑
 *  视图（View）：用来和用户进行交互。
 *  好处:代码的维护性更好.
 *       实现复用
 * 2.springMvc的工作原理
 *      1.spring mvc请所有的请求都提交给DispatcherServlet,它会委托应用系统的其他模块负责负责对请求进行真正的处理工作。
 *      2.DispatcherServlet查询一个或多个HandlerMapping,找到处理请求的Controller.
 *      3.DispatcherServlet请请求提交到目标Controller
 *      4.Controller进行业务逻辑处理后，会返回一个ModelAndView
 *      5.Dispathcher查询一个或多个ViewResolver视图解析器,找到ModelAndView对象指定的视图对象
 *      6.视图对象负责渲染返回给客户端。
 * 3.springMvc的Controller 是单例的，
 *       1.为了性能。
 *       2.不需要多例。
 */
public class SpringMvcTestController {
}
