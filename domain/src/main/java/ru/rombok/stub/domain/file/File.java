package ru.rombok.stub.domain.file;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.DomainObject;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

/**
 * Stored file (script, photo, etc.)
 */
@Entity
@Getter
@Setter
@ToString
public class File extends DomainObject {

    /**
     * Unique identifier
     */
    private UUID uuid;

    /**
     * Type of storage, that keeps this file
     */
    @Enumerated(EnumType.STRING)
    private FileStorageType fileStorageType;

    /**
     * file content (raw data/storage URL)
     */
    @Lob
    @Basic(fetch = LAZY)
    @ToString.Exclude
    private byte[] content;

    /**
     * Upload date & time
     */
    private Instant uploadedAt;
}
