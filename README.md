### 扩展 javax.validation
* @MultipleString 允许参数为多个String值                
* @MultipleInt 允许参数为多个int值
* @ListSize 校验集合的元素个数

### 如何使用(示例代码)
以下示例说明了在path，URL参数，以及请求参数body中分别如何使用自定义注解，如果需要Spring直接拦截方法参数上的自定义注解需要在controller类
上配置@Validated

* CustomValidTestController.java
```java
import com.darren.validation.ext.MultipleInt;
import com.darren.validation.ext.MultipleString;
import com.tandong.entity.PutRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author tandong.td
 */
@Controller
@RequestMapping("valid/custom")
@Validated
public class CustomValidTestController {

    @GetMapping("/value/id/{id}")
    public @ResponseBody String pathValid(
            @PathVariable
            @MultipleInt(values = {1, 2, 3}, message = "参数不合法") int id){
        return "Hello " + id;
    }

    @GetMapping("/value/name/{name}")
    public @ResponseBody String pathValid(
            @PathVariable
            @MultipleString(values = {"a", "b", "c"}, message = "参数不合法") String name){
        return "Hello " + name;
    }

    @GetMapping("/value")
    public @ResponseBody String parameterValid(
            @RequestParam
            @MultipleString(values = {"a", "b", "c"}, message = "参数不合法") String name){
        return "Hello " + name;
    }

    @PutMapping
    public @ResponseBody String bodyValid(@Valid @RequestBody PutRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "Hello " + request.getName();
    }
}
``` 
* PutRequest.java
```java
import com.darren.validation.ext.ListSize;
import com.darren.validation.ext.MultipleInt;
import com.darren.validation.ext.MultipleString;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tandong.td
 */
@Data
public class PutRequest implements Serializable {

    @MultipleInt(values = {1, 2, 3}, message = "参数不合法")
    int id;

    @MultipleString(values = {"a", "b", "c"}, message = "参数不合法")
    String name;

    @ListSize(min = 0, max = 1, message = "ids不能为空且大小不能超过指定范围(0-1)")
    List<String> ids;

}
```
