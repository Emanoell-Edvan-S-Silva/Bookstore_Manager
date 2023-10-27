package wdabookstore.bookstoremanager.exceptions.error_message;

import lombok.Getter;

@Getter
public enum ProblemType {
    BUSINESS_ERROR("/business-error", "ProblemType.Title.BusinessError"),
    DUPLICATE_FIELD("/duplicate-field", "ProblemType.Title.DuplicateField"),
    INVALID_PARAMETER("/invalid-parameter", "ProblemType.Title.InvalidParameter"),
    MALFORMED_BODY("/malformed-body", "ProblemType.Title.MalformedBody"),
    RESOURCE_NOT_FOUND("/resource-not-found", "ProblemType.Title.ResourceNotFound"),
    INVALID_DATE_INPUT("/invalid-date-input", "ProblemType.Title.InvalidDateInput"),
    RENTAL_FINISH_ERROR("/rental-finish-error", "ProblemType.Title.RentalFinishError"),

    UNAUTHORIZED("/unauthorized", "ProblemType.Title.Unauthorized");

    private final String code;
    private final String uri;

    ProblemType(String path, String code) {
        this.uri = String.format("%s%s", "http://localhost/", path);
        this.code = code;
    }

    public String getTitle() {
        return code;
    }
}
