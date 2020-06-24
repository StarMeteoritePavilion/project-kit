package com.lcn29.kit.validation;

import com.lcn29.kit.util.CollectionMapUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * a util used for valid args by hibernate-validator
 * </pre>
 *
 * @author LCN
 * @date 2020-02-18 14:12
 */
public class ValidatorUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * valid the object
     * @param object the need-valid object
     * @param groups the valid groups
     * @param <T>
     * @return valid result and error msg list
     */
    public static <T> ValidResult validate(T object, Class<?>... groups) {

        ValidResult result = new ValidResult();
        if (Objects.isNull(object)) {
            return result;
        }
        // @link {javax.validation.groups.Default} will be used if the groups is null
        Set<ConstraintViolation<T>> constraintViolations = groups != null && groups.length > 0 ?
            validator.validate(object, groups) : validator.validate(object);
        // valid success
        if (CollectionMapUtil.isEmpty(constraintViolations)) {
            return result;
        }

        // valid fail
        result.setValueResult(false);
        result.setErrorMsgList(constraintViolations.stream().map(item -> item.getPropertyPath() + ":" + item.getMessage()
        ).collect(Collectors.toList()));

        return result;
    }

    /**
     * valid result object
     */
     static class ValidResult {

        /** the default valid result is true */
        private boolean valueResult = true;

        /** if the validResult is false, the obj hold the valid wrong msg  */
        private List<String> errorMsgList;

        boolean getValueResult() {
            return valueResult;
        }

        private void setValueResult(boolean valueResult) {
            this.valueResult = valueResult;
        }

        List<String> getErrorMsgList() {
            return errorMsgList;
        }

        private void setErrorMsgList(List<String> errorMsgList) {
            this.errorMsgList = errorMsgList;
        }
    }

}
