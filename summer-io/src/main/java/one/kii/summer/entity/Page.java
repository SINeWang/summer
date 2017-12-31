package one.kii.summer.entity;

import lombok.Data;

import java.util.List;

@Data
class Page<T> {
    List<T> data;

    int total;
}