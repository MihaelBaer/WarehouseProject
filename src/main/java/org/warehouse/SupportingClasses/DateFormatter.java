package org.warehouse.SupportingClasses;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Functional interface for formatting a date in docs
 */
@Service
public interface DateFormatter {

    public LocalDate formatDate(String dateToFormat);
}
