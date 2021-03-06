package ru.rombok.stub.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
}
