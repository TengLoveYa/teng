import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李腾
 * @datetime 2023/10/5 14:52
 * @description 类对象
 */
public class GO {
    //数据库名
    private static final String DB="building_core";
    //项目名
    private static final String PROJECT_NAME="service-core";
    //父级模块名
    private static final String MODULE_NAME = "";
    //生成的表名
    private static final String[] TABLE_NAME = {};
    //过滤前缀
    private static final String[] TABLE_Prefix = {""};
    //数据库
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/"+DB+"?useUnicode=true&characterEncoding=utf8&useSSL=false";
    //用户名
    private static final String USERNAME = "root";
    //密码
    private static final String PASSWORD = "ROOT";
    //作者
    private static final String AUTHOR = "李腾";
    //父级包名
    private static final String PARENT = "com.zhly";
    //生成位置xml
    private static final String PATH_INFO = System.getProperty("user.dir") + "/"+PROJECT_NAME+"/src/main/resources/dao/";
    //生成位置
    private static final String OUTPUT_DIR = System.getProperty("user.dir") + "/"+PROJECT_NAME+"/src/main/java";


    //填充策略
    private static final Column[] FILLS = {
            new Column("create_time", FieldFill.INSERT),
            new Column("start_time", FieldFill.INSERT),
            new Column("end_time", FieldFill.INSERT),
            new Column("update_time", FieldFill.INSERT_UPDATE),
    };

    //自定义模板位置
    private static final HashMap<String, String> CUSTOM_FILE = new HashMap<String, String>() {{
        put("utils/BaseService.java", "/temp/baseService.java.ftl");
        put("utils/BaseServiceImpl.java", "/temp/baseServiceImpl.java.ftl");
        put("utils/MyResult.java", "/temp/myResult.java.ftl");
        put("utils/MyFormat.java", "/temp/myFormat.java.ftl");
        put("config/PageConfig.java", "/temp/pageConfig.java.ftl");
        put("config/LocalDateTimeConfig.java", "/temp/localDateTimeConfig.java.ftl");
    }};

    public static void main(String[] args) {
            FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                    .globalConfig(builder -> {
                        builder.author(AUTHOR) // 设置作者
                               // .enableSwagger() // 开启 swagger 模式
                                .fileOverride() // 覆盖已生成文件
                                .outputDir(OUTPUT_DIR); // 指定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent(PARENT) // 设置父包
                                .entity("po")
                                .other("")
                                .mapper("dao")
                                .xml("dao.xml")
                                .moduleName(MODULE_NAME) // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, PATH_INFO+MODULE_NAME)); // 设置mapperXml生成路径
                    })
                    .strategyConfig(o -> {
                        o.addInclude(TABLE_NAME) // 设置需要生成的表名
                                .addTablePrefix(TABLE_Prefix)
//                                .addFieldPrefix("lt_")
                                //设置表
                                .entityBuilder()
                                .enableTableFieldAnnotation()
                                //开启lombok
                                .enableLombok()
                                //每个继承model
                                .enableActiveRecord()
                                //逻辑删除
                                .logicDeleteColumnName("status")
                                //自动填充
                                .addTableFills(FILLS)
                                //表结束
                                .build()
                                //mapper
                                .mapperBuilder()
                                //改后缀
                                .convertMapperFileName(obj -> obj + "DAO")
                                .convertXmlFileName(obj -> obj + "DAO")
                                .enableBaseColumnList()
                                .enableBaseResultMap()
                                //mapper结束
                                .build()
                                .controllerBuilder()
                                .enableRestStyle().build()
                        ; // 设置过滤表前缀
                    })
                    // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .templateEngine(new FreemarkerTemplateEngine() {

                        @Override
                        protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
                            String otherPath = this.getPathInfo(OutputFile.other);
                            customFile.forEach((key, value) -> {
                                StringBuffer stringBuffer = new StringBuffer(OUTPUT_DIR);
                                Arrays.stream(PARENT.split("\\u002E")).forEach(obj -> stringBuffer.append("/").append(obj));
                                File file = new File(stringBuffer.append("/").append(MODULE_NAME).append("/").append(key).toString());
                                if (!file.exists())
                                    this.outputFile(file, objectMap, value);
                            });
                        }
                    })
                    .templateConfig(builder -> {
                        builder.entity("/temp/entity.java")
                                .service("/temp/service.java")
                                .serviceImpl("/temp/serviceImpl.java")
                                .mapper("/temp/mapper.java")
                                .mapperXml("/temp/mapper.xml")
                                .controller("/temp/controller.java");
                    }).injectionConfig(
                    consumer -> {
                        consumer.customFile(CUSTOM_FILE);
                    }
            )
                    .execute();


    }
}