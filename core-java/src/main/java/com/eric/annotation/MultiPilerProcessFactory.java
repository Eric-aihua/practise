package com.eric.annotation;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class MultiPilerProcessFactory implements AnnotationProcessorFactory {
	
	public Collection<String> supportedOptions() {
		return Collections.EMPTY_LIST;
	}
	
	public Collection<String> supportedAnnotationTypes() {
		return Arrays.asList("com.eric.annotation.ExtraInterface");
	}
	
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
		return new MultipilerProcess(env);
	}
	
}
