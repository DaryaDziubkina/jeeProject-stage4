package by.gsy.epamProject.model.beans;


import by.gsy.epamProject.model.constans.Constants;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public enum NoteTypes {

    TODAY("Today", false, false, false, true) {
        @Override
        public Date getDate() {
            return Date.valueOf(LocalDate.now());
        }

        @Override
        public Date getDateMax() {
            return Date.valueOf(LocalDate.now());
        }
    },
    TOMORROW("Tomorrow", false, false, false, true) {
        @Override
        public Date getDateMax() {
            return Date.valueOf(LocalDate.now().plusDays(Constants.Date.DAYS_TO_NEXT_DAY));
        }

        @Override
        public Date getDate() {
            return Date.valueOf(LocalDate.now().plusDays(Constants.Date.DAYS_TO_NEXT_DAY));
        }

        @Override
        public Date getDateMin() {
            return Date.valueOf(LocalDate.now().plusDays(Constants.Date.DAYS_TO_NEXT_DAY));
        }
    },
    SOMEDAY("Someday", false, false, true, true) {
        @Override
        public Date getDateMin() {
            return Date.valueOf(LocalDate.now().plusDays(Constants.Date.DAYS_TO_NEXT_TWO_DAY));
        }
    },
    FIXED("Fixed", true, false, true, false),
    RECYCLE_BIN("Recycle Bin", false, true, true, false),
    ALL("All", true, true, true, true);


    private String value;
    private boolean isFixed;
    private boolean isDeleted;
    private boolean isDateShow;
    private boolean isFixedShow;
    private Date date;

    private NoteTypes(String value, boolean isFixed, boolean isDeleted, boolean isDateShow, boolean isFixedShow) {
        this.value = value;
        this.isFixed = isFixed;
        this.isDeleted = isDeleted;
        this.isDateShow = isDateShow;
        this.isFixedShow = isFixedShow;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDateShow() {
        return isDateShow;
    }

    public void setIsDateShow(boolean isDateShow) {
        this.isDateShow = isDateShow;
    }

    public boolean isFixedShow() {
        return isFixedShow;
    }

    public void setIsFixedShow(boolean isFixedShow) {
        this.isFixedShow = isFixedShow;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateMax() {
        return Date.valueOf(LocalDate.now().plusDays(Constants.Date.DAYS));
    }

    public Date getDateMin() {
        return Date.valueOf(LocalDate.now().minusDays(Constants.Date.DAYS));
    }

    public String getDateFormatted() {
        return new SimpleDateFormat(Constants.Date.DATE_PARSE_PATTERN).format(getDate());
    }

    public String getDateFormatted2() {
        return new SimpleDateFormat(Constants.Date.DATE_PARSE_PATTERN_2).format(getDate());
    }

}
