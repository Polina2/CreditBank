package ru.neoflex.edu.java.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.edu.java.dto.LoanStatementRequestDto;
import ru.neoflex.edu.java.entity.Client;
import ru.neoflex.edu.java.entity.json.Passport;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

class LoanStatementRequestMapperTest {

    private final LoanStatementRequestMapper mapper = new LoanStatementRequestMapperImpl();

    @Test
    void toClient() {
        LoanStatementRequestDto dto = new LoanStatementRequestDto(
                BigDecimal.valueOf(3000000),
                12,
                "aaa", "eee", "mmm",
                "qq@mail.ru",
                LocalDate.of(2002, 12,2),
                "1234", "123456"
        );
        Client expectedClient = new Client();
        Passport passport = new Passport();
        passport.setNumber("123456");
        passport.setSeries("1234");
        expectedClient.setFirstName("aaa");
        expectedClient.setLastName("eee");
        expectedClient.setMiddleName("mmm");
        expectedClient.setEmail("qq@mail.ru");
        expectedClient.setBirthDate(Date.valueOf(LocalDate.of(2002, 12,2)));
        expectedClient.setPassport(passport);

        Client actualClient = mapper.toClient(dto);

        Assertions.assertThat(actualClient)
                .usingRecursiveComparison()
                .ignoringFields("birthDate")
                .isEqualTo(expectedClient);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Assertions.assertThat(sdf.format(actualClient.getBirthDate()))
                .isEqualTo(sdf.format(expectedClient.getBirthDate()));
    }
}