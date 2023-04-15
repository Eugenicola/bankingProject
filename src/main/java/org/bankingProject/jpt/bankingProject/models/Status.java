package org.bankingProject.jpt.bankingProject.models;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Status {
    FROZEN,
    ACTIVE
}
