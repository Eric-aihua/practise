package com.eric.annotation;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.PrintWriter;

public class MultipilerProcess implements AnnotationProcessor {
	private AnnotationProcessorEnvironment annotationProcessorEnvironment	= null;
	
	public MultipilerProcess(AnnotationProcessorEnvironment annotationProcessorEnvironment) {
		this.annotationProcessorEnvironment = annotationProcessorEnvironment;
	}
	
	public void process() {
		try {
			for (TypeDeclaration type : annotationProcessorEnvironment.getTypeDeclarations()) {
				ExtraInterface extraInterface = type.getAnnotation(ExtraInterface.class);
				// Get Interface name
				String name = extraInterface.name();
				PrintWriter pw = annotationProcessorEnvironment.getFiler().createSourceFile(name);
				pw.write("package " + type.getPackage() + ";");
				pw.write("public interface" + name + "{");
				for (MethodDeclaration method : type.getMethods()) {
					if (method.getModifiers().contains(Modifier.PUBLIC) && (!method.getModifiers().contains(Modifier.STATIC))) {
						String parameters = getParameters(method);
						pw.write("public " + method.getReturnType() + method.getSimpleName() + parameters);
					}
				}
				pw.write("}");
				pw.flush();
				pw.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getParameters(MethodDeclaration method) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (ParameterDeclaration parameterDeclaration : method.getParameters()) {
			sb.append(parameterDeclaration.getType() + " " + parameterDeclaration.getSimpleName() + ",");
		}
		
		return sb.toString().substring(0, sb.toString().length() - 2) + ")";
	}
	
}
