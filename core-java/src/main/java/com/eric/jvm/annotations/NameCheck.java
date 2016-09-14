package com.eric.jvm.annotations;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;

public class NameCheck {
	private final Messager messager;
	private NameCheckScanner checkScanner;

	public NameCheck(ProcessingEnvironment processing) {
		this.messager = processing.getMessager();
		checkScanner = new NameCheckScanner();
	}

	public void check(Element element) {
		checkScanner.scan(element);
	}

	public class NameCheckScanner extends ElementScanner6<Void, Void> {
		@Override
		public Void visitType(TypeElement element, Void p) {
			scan(element.getTypeParameters(), p);
			checkCamelName(element, true);
			super.visitType(element, p);
			return p;

		}

		/**
		 * (1)not check <init> method (2)method can't equal with Class
		 * or interface name
		 * */
		@Override
		public Void visitExecutable(ExecutableElement element, Void p) {
			String methodName = element.getSimpleName().toString();
			if (methodName.equals(element.getEnclosingElement().getSimpleName().toString())) {
				messager.printMessage(Diagnostic.Kind.WARNING, element.getKind() + " " + methodName
						+ " can't be with it's owner !");
			}
			if (!methodName.equals("<init>")) {
				checkCamelName(element, false);
			}
			return p;

		}

		@Override
		public Void visitVariable(VariableElement variableElement, Void p) {
			// constant field
			if (isConstantField(variableElement)) {
				checkCapsName(variableElement);
			} else {
				// not a constant field
				checkCamelName(variableElement, false);
			}
			return p;
		}

		private boolean isConstantField(VariableElement variableElement) {
			Object constantValue = variableElement.getConstantValue();
			return constantValue != null;
		}

		private void checkCapsName(VariableElement element) {
			String elementName = element.getSimpleName().toString();
			boolean nameValid = checkElementnameValid(elementName);
			if (nameValid) {
				for (char ch : elementName.toCharArray()) {
					if (Character.isLowerCase(ch)) {
						messager.printMessage(Diagnostic.Kind.WARNING, element.getKind() + " "
								+ elementName + " not validate for constant filed !");
						break;
					}
				}
			} else {
				messager.printMessage(Diagnostic.Kind.ERROR, element.getKind() + " " + elementName
						+ " is Valid!");
			}
		}

		/**
		 * 
		 * @param element
		 *                check element
		 * @param isClassOrInterface
		 *                if element is class or interface, element's
		 *                name must begin with upper case, parameter is
		 *                true
		 */
		private void checkCamelName(Element element, boolean isClassOrInterface) {
			String elementName = element.getSimpleName().toString();
			boolean nameValid = checkElementnameValid(elementName);
			if (nameValid) {

				if (isClassOrInterface) {

					if (!Character.isUpperCase(elementName.codePointAt(0))) {
						messager.printMessage(Diagnostic.Kind.WARNING, element.getKind() + " "
								+ elementName + " not validate for class or interface");
					}
				} else {
					if (Character.isUpperCase(elementName.codePointAt(0))) {
						messager.printMessage(Diagnostic.Kind.WARNING, element.getKind() + " "
								+ elementName + " not validate for notmal field!");
					}
				}
			} else {
				messager.printMessage(Diagnostic.Kind.ERROR, element.getKind() + " " + elementName
						+ " is Valid!");
			}
		}

		/**
		 * java element names rules: (1)just could begin with "_" or
		 * character (2)all name just could contains "_", character,
		 * integer.
		 * 
		 * @param string
		 * @return valid result
		 */
		private boolean checkElementnameValid(String elementName) {
			boolean checkResult = true;
			if (Character.isJavaIdentifierStart(elementName.charAt(0))) {
				for (char ch : elementName.toCharArray()) {
					if (!Character.isJavaIdentifierPart(ch)) {
						checkResult = false;
						break;
					}
				}
			} else {
				checkResult = false;
			}
			return checkResult;
		}

	}

}
