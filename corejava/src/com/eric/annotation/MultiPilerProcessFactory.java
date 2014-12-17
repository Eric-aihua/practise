package com.eric.annotation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class MultiPilerProcessFactory implements AnnotationProcessorFactory {
	
	@Override
	public Collection<String> supportedOptions() {
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Arrays.asList("com.eric.annotation.ExtraInterface");
	}
	
	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
		return new MultipilerProcess(env);
	}
	
}
