package org.avaliacao.ap002.auth.entity;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
