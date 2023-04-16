package org.bankingProject.jpt.bankingProject.models.Accounts;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Status {
    FROZEN,
    ACTIVE
}
