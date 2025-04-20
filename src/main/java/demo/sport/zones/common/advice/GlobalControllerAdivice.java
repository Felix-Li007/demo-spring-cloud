package demo.sport.zones.common.advice;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalControllerAdivice
{
    //WebDataBinder是用来绑定请求参数到指定的属性编辑器，可以继承WebBindingInitializer
    //来实现一个全部controller共享的dataBiner Java代码
    @InitBinder
    public void dataBind(WebDataBinder binder)
    {
        //处理GET方式请求时参数空字符串转化为null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
