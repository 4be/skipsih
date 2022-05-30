function parseDateValue(rawDate) {
    if (rawDate == '') {
        return "";
    } else {
        const dateArray = rawDate.split("-");
        const parsedDate = new Date(parseInt(dateArray[0]), parseInt(dateArray[1]) - 1, parseInt(dateArray[2]));  // -1 because months are from 0 to 11
        return parsedDate;
    }
}

function formatDate(date, convertMode = false, dateFormat = 'ymd') {
    let result = '', day, month, year;
    let indoMonth = ['', 'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
    if (date != '') {
        if (dateFormat == 'mdy') {
            if (convertMode) {
                month = date.split("/")[0];
                if (parseInt(month) < 10) {
                    month = "0" + month;
                }
                day = date.split("/")[1];
                year = date.split("/")[2];
                result += year + "-" + month + "-" + day;
            } else {
                month = date.split("/")[0];
                if (parseInt(month) < 10) {
                    month = "0" + month;
                }
                day = date.split("/")[1];
                year = date.split("/")[2];
                result += day + " " + indoMonth[parseInt(month)] + " " + year;
            }
        } else if(dateFormat == 'ymd') {
            year = date.split("-")[0];
            month = date.split("-")[1];
            day = date.split("-")[2];
            result += day + " " + indoMonth[parseInt(month)] + " " + year;
        }
    }
    return result;
}

function exportRange(min, max) {
    let result = ' ';
    if (min != '' && max != '') {
        result = "Periode " + formatDate(min) + " - " + formatDate(max);
    } else if (min != '' && max == '') {
        result = "Periode " + formatDate(min) + " - Sekarang";
    } else if (min == '' && max != '') {
        result = "Periode hingga " + formatDate(max);
    }
    return result;
}

$(document).ready(function () {
    $('#min').datepicker({
        uiLibrary: 'bootstrap',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'id',
        immediateUpdates: true,
        todayHighlight: true
    });
    $('#max').datepicker({
        uiLibrary: 'bootstrap',
        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'id',
        immediateUpdates: true,
        todayHighlight: true
    });
    $('#imageClockModal').on('shown.bs.modal', function (e) {
        var id = $(e.relatedTarget).data('link');
        $('#imageClock').attr('src', 'http://' + id);
        $('#downloadImageClock').attr('download', id.split("/")[2]);
        $('#downloadImageClock').attr('target', '_blank');
        $('#downloadImageClock').attr('href', 'http://' + id);
    });
    $('#imageClockModal').on('hidden.bs.modal', function () {
        $('#imageClock').attr('src', '/img/loading.gif');
    })
});
