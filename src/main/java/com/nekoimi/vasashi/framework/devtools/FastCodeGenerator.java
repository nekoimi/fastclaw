package com.nekoimi.vasashi.framework.devtools;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.nekoimi.vasashi.framework.mybatis.BaseEntity;
import com.nekoimi.vasashi.framework.mybatis.BaseMapper;
import com.nekoimi.vasashi.framework.mybatis.ReactiveCrudService;
import com.nekoimi.vasashi.framework.mybatis.ReactiveICrudService;
import lombok.Builder;
import lombok.Getter;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * nekoimi  2022/2/17 10:59
 */
@Getter
@Builder(builderMethodName = "of")
public class FastCodeGenerator {
    private final String outputDir;
    private final String packageName;
    private final String schemaName;
    private final List<String> tableName;
    private final String tablePrefix;
    private final DataSource dataSource;
    private final Consumer<TemplateConfig.Builder> templateConsumer;

    public void execute() {
        FastAutoGenerator.create(new DataSourceConfig
                .Builder(dataSource)
                .schema(schemaName))
                .globalConfig(builder -> {
                    builder.author("devtools") // 设置作者
                            .disableOpenDir()
                            .fileOverride()
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(packageName); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName)
                            .enableSchema()
                            .addTablePrefix(tablePrefix)
                            .entityBuilder()
                            .addSuperEntityColumns("id", "created_at", "updated_at", "deleted")
                            .superClass(BaseEntity.class)

                            .mapperBuilder()
                            .superClass(BaseMapper.class)

                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .superServiceClass(ReactiveICrudService.class)

                            .serviceBuilder()
                            .formatServiceImplFileName("%sServiceImpl")
                            .superServiceImplClass(ReactiveCrudService.class); // 设置需要生成的表名
                })
                .templateConfig(builder -> builder.entity("devtools/entity.java")
                        .controller("devtools/controller.java")
                        .mapper("devtools/mapper.java")
                        .mapperXml("devtools/mapper.xml")
                        .service("devtools/service.java")
                        .serviceImpl("devtools/serviceImpl.java"))
                .templateConfig(Optional.ofNullable(templateConsumer).orElse(builder -> {
                }))
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
