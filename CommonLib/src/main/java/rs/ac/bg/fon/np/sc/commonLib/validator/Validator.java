/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import rs.ac.bg.fon.np.sc.commonLib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.commonLib.domen.StavkaSkiPasa;

/**
 *
 * @author UrosVesic
 */
public class Validator {

    private final List<String> validationErros;

    private Validator() {
        validationErros = new ArrayList();
    }

    public static Validator startValidation() {
        return new Validator();
    }

    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateFieldsNotNullOrEmpty(OpstiDomenskiObjekat odo) {
        List<String> lista = new ArrayList<>();
        try {
            lista = odo.validirajPolja();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        if (!lista.isEmpty()) {
            lista.forEach(poruka -> validationErros.add(poruka));
        }
        return this;
    }

    public Validator validateNotNullOrEmpty(Collection value, String errorMessage) throws ValidationException {
        if (value == null || value.isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateValueIsNumber(String value, String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                new BigDecimal(value);
            } else {
                this.validationErros.add(errorMessage);
            }
        } catch (NumberFormatException nfe) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateValueIsDate(String value, String pattern, String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.parse(value);
            } else {
                this.validationErros.add(errorMessage);
            }
        } catch (ParseException ex) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator validateListIsNotEmpty(List list, String errorMessage) throws ValidationException {
        if (list == null || list.isEmpty()) {
            this.validationErros.add(errorMessage);
        }
        return this;
    }

    public Validator throwIfInvalide() throws ValidationException {
        if (!validationErros.isEmpty()) {
            throw new ValidationException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
        return this;
    }

    public Validator throwIfInvalideRuntime() {
        if (!validationErros.isEmpty()) {
            throw new ValidationRuntimeException(this.validationErros.stream().collect(Collectors.joining("\n")));
        }
        return this;
    }

    public Validator validateWorkingHoursFormat(String radnoVreme, String poruka) {
        int i = 0;
        if (radnoVreme == null) {
            return this;
        }
        String[] sati = radnoVreme.split("-");
        if (sati.length != 2) {
            this.validationErros.add(poruka);
            return this;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            sdf.parse(sati[0]);
            sdf.parse(sati[1]);
        } catch (ParseException ex) {
            i++;
            //this.validationErros.add(poruka);
        }
        sdf = new SimpleDateFormat("HH");
        try {
            sdf.parse(sati[0]);
            sdf.parse(sati[1]);
        } catch (ParseException ex) {
            i++;
            //this.validationErros.add(poruka);
        }
        if (i == 2) {
            this.validationErros.add(poruka);
        }
        return this;
    }

    public Validator validateEmailFormat(String email, String poruka) {
        if (!email.contains("@")) {
            this.validationErros.add(poruka);
        }
        return this;
    }

    public Validator validateGreaterThanZero(long id, String poruka) {
        if (id <= 0) {
            this.validationErros.add(poruka);
        }
        return this;
    }

    public Validator validateIfItemsExistForPeriod(StavkaSkiPasa stavka, SkiPas skiPas, String poruka) {
        for (StavkaSkiPasa stavkaPostojeca : skiPas.getStavkeSkiPasa()) {

            if (stavka != stavkaPostojeca) {
                if (stavka.getZavrsetakVazenja() != null && stavka.getZavrsetakVazenja().after(stavkaPostojeca.getPocetakVazenja()) && stavka.getZavrsetakVazenja().before(stavkaPostojeca.getZavrsetakVazenja())) {
                    this.validationErros.add(poruka);
                    return this;
                }
                if (stavka.getPocetakVazenja() != null && stavka.getPocetakVazenja().after(stavkaPostojeca.getPocetakVazenja()) && stavka.getPocetakVazenja().before(stavkaPostojeca.getZavrsetakVazenja())) {
                    this.validationErros.add(poruka);
                    return this;
                }
                if (stavka.getPocetakVazenja() != null && stavka.getPocetakVazenja().compareTo(stavkaPostojeca.getPocetakVazenja()) == 0) {
                    this.validationErros.add(poruka);
                    return this;
                }
                if (stavka.getZavrsetakVazenja() != null && stavka.getZavrsetakVazenja().compareTo(stavkaPostojeca.getZavrsetakVazenja()) == 0) {
                    this.validationErros.add(poruka);
                    return this;
                }
            }
        }
        return this;
    }

    public Validator validateIfDateIsInSeason(Date date, String season, String poruka) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int godina = calendar.get(Calendar.YEAR);
        int mesec = calendar.get(Calendar.MONTH);
        String[] godineSezone = season.split("/");
        if ((godineSezone[0].equals(godina + "") && mesec > 5) || (godineSezone[1].equals(godina + "") && mesec <= 5)) {
            return this;
        } else {
            this.validationErros.add(poruka);
            return this;
        }
    }

    public Validator validateSeasonFormat(String sezona, String poruka) {
        String[] godine = sezona.split("/");
        try {
            if (godine == null || godine.length != 2) {
                throw new IllegalArgumentException();
            }
            Integer.parseInt(godine[0]);
            Integer.parseInt(godine[1]);
        } catch (NumberFormatException ex) {
            this.validationErros.add(poruka);
        } catch (IllegalArgumentException ex) {
            this.validationErros.add(poruka);
        }

        return this;
    }

}
