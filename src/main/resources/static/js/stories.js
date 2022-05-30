$(document).ready(function () {
    $.fn.dataTable.ext.search.push(
        function (settings, data, index, rowData, counter) {
            var min = parseDateValue($('#min').val());
            var max = parseDateValue($('#max').val());
            var current = parseDateValue(data[2].split("T")[0]);
            var flag = false;
            if ((min == '' && max == '') ||
                (min <= current && max == '') ||
                (min == '' && current <= max) ||
                (min <= current && current <= max)) {
                flag = true;
            } else {
                flag = false;
            }
            return flag;
        }
    );

    var t = $('#dataStories').DataTable({
        dom: "<'row'<'col-md-3'l><'col-md-5 text-left'B><'col-md-4'f>>" +
            "<'row'<'col-md-12'tr>>" +
            "<'row'<'col-md-5'i><'col-md-7'p>>",
        buttons: [{
            text: "<i class=\"fas fa-download\"></i> Export CSV",
            extend: 'csv',
            exportOptions: {
                columns: [1, 2, 3]
            }
        }, {
            text: "<i class=\"fas fa-download\"></i> Export Excel",
            extend: 'excel',
            exportOptions: {
                columns: [1, 2, 3]
            },
            title: function () {
                let date = new Date().toLocaleDateString();
                return 'SIKASEP - Rekap Data Stories (dibuat pada ' + formatDate(date,false,'mdy') + ')';
            },
            messageTop: function () {
                return exportRange($('#min').val(),$('#max').val());
            }
        }],
        lengthMenu: [
            [10, 25, 50, -1],
            ['10 rows', '25 rows', '50 rows', 'Show all']
        ],
        ajax: {
            url: "/api/stories/list/desc/",
            type: "GET",
            headers: {Authorization: localStorage.getItem("token")},
            dataSrc: function (result) {
                return result;
            },
            error: function (result) {
                if (result.status == 401) {
                    alert(result.responseJSON.message);
                    localStorage.removeItem("token");
                    location.href = "/";
                }
            }
        },
        columns: [
            {data: null, "class": "tbl-center"},
            {data: "user_id.nama", class: "tbl-center"},
            {
                "data": 'date_published',
                "class": "tbl-center",
                "render": function (data, type, row, meta) {
                    let datetime = data.split("T");
                    if (type == 'display') {
                        let time = datetime[1].substr(0, 8);
                        data = formatDate(datetime[0]) + " | " + time;
                    }
                    return data;
                }
            },
            {data: "description", class: "tbl-center"},
            {
                data: "url_foto_stories",
                class: "tbl-center",
                render: function (data, type, row, meta) {
                    if (type == 'display') {
                        let id = data;
                        if (id != null) {
                            if (location.hostname == 'localhost') {
                                id = id.replace("/", ":8080/");
                            }
                            data = '<a id="' + id + '" href="#" class="btn btn-primary finger-pointer" data-toggle="modal" data-target="#imageClockModal" data-link="' + id + '"><i class="fas fa-eye"></i></a>';
                        } else {
                            data = "Tanpa Foto";
                        }
                    }
                    return data;
                }
            },
        ],
        columnDefs: [{
            searchable: false,
            orderable: false,
            targets: [0, 4]
        }],
        ScrollX: true,
        order: [[1, 'asc']],
    });
    t.on('order.dt search.dt', function () {
        t.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
            t.cell(cell).invalidate('dom');
        });
    });
    $('#min, #max').change(function () {
        t.draw();
    });
    $('#btnClear').on('click', () => {
        $('#min').val("");
        $('#max').val("");
        t.draw();
    });
    $('#btnToday').on('click', function () {
        var now = new Date();
        var dateNow = formatDate(now.toLocaleDateString(), true,'mdy');
        $('#min').val(dateNow);
        $('#max').val(dateNow);
        t.draw();
    });
});
