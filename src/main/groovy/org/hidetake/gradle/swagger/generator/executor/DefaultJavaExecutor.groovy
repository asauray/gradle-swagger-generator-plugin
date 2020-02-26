package org.hidetake.gradle.swagger.generator.executor

import groovy.util.logging.Slf4j
import org.gradle.api.Project
import org.gradle.process.ExecResult
import org.gradle.process.JavaExecSpec
import org.hidetake.gradle.swagger.generator.codegen.JavaExecOptions

@Slf4j
@Singleton
class DefaultJavaExecutor implements JavaExecutor {
    List<String> jvmArgs = []

    @Override
    ExecResult execute(Project project, JavaExecOptions javaExecOptions) {
        log.info("Executing Java command: $javaExecOptions")
        project.javaexec { JavaExecSpec spec ->
            spec.classpath(javaExecOptions.classpath)
            spec.main = javaExecOptions.main
            spec.args = javaExecOptions.args
            spec.systemProperties(javaExecOptions.systemProperties)
            spec.jvmArgs(['-Xmx512m', '-Xms512m'])//FIXME
        }
    }
}