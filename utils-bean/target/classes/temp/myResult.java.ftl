package ${package.Other}utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author 李腾
* @datetime 2023/8/27 16:13
* @description 类对象
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyResult${"<T>"} {
    private Integer code;
    private T data;
    private String message;
    public static MyResult ok(){
    return new MyResult(200,null,null);
    }
    public static ${"<T>"} MyResult ok(T data){
        return new MyResult(200,data,null);
        }
        public static MyResult no(){
        return new MyResult(500,null,null);
        }
        public static MyResult no(String message){
        return new MyResult(500,null,message);
        }
        public static MyResult no(Integer code, String message){
        return new MyResult(code,null,message);
        }
        public static MyResult or(boolean f){
        return f?ok():no();
        }
        public static ${"<T>"} MyResult or(boolean f, T data){
            return f?ok(data):no();
            }
            }
