package com.airbnb.epoxy;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import static com.airbnb.epoxy.Utils.removeSetPrefix;

class DataBindingAttributeInfo extends AttributeInfo {

  DataBindingAttributeInfo(DataBindingModelInfo modelInfo, ExecutableElement setterMethod,
      HashCodeValidator hashCodeValidator) {
    VariableElement paramElement = setterMethod.getParameters().get(0);
    this.name = removeSetPrefix(setterMethod.getSimpleName().toString());
    typeName = TypeName.get(paramElement.asType());
    typeMirror = paramElement.asType();
    modelName = modelInfo.getGeneratedName().simpleName();
    modelPackageName = modelInfo.getGeneratedName().packageName();
    useInHash = hashCodeValidator.implementsHashCodeAndEquals(typeMirror);
    ignoreRequireHashCode = false;
    generateSetter = true;
    generateGetter = true;
    hasFinalModifier = false;
    packagePrivate = false;
    isGenerated = true;
  }
}
