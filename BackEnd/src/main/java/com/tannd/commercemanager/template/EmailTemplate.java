package com.tannd.commercemanager.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplate {
    private String title;
    private String content;

    public String toEmail() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Template</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h3>" + title + "</h3>\n" +
                "    <div>\n" +
                "       " + content + "\n" +
                "    </div>\n" +
                "    <img src='cid:product'>" +
                "</body>\n" +
                "</html>";
    }
}
