package one.kii.summer.beans.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ScopeValue {

    String id;

    String entityId;

    String scope;

    String value;

    String operatorId;

    Date beginTime;

    Date endTime;
}
