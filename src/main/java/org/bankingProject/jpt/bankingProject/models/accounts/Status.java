package org.bankingProject.jpt.bankingProject.models.accounts;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Status {
    FROZEN,
    ACTIVE
}
