function openbox(id) {
    "use strict";
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function openbox(id) {
    "use strict";
    var div = document.getElementById(id);

    if(div.style.display == 'block') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

var photoService = {
    pos: 0,
    popUp: 'photoPopUp',
    mode: 0,

    savePhoto: function () {
        "use strict";
        var form = document.getElementById("avatar");
        if (!form.photo.value) {
            alert("Please, fill required fields");
            return false;
        }
        var file = form.photo.files[0];
        if(file != undefined && file.size > 1024* 1024) {
            alert("Too big image! Maximum size of image is 1 MB");
            return false;
        }

        openbox(this.popUp);


        var files = document.getElementById('photo').files;
        if (files.length > 0) {
            getBase64Image(files[0]);
        }
        form.reset();
    },

    addPhoto: function () {
        "use strict";
        this.mode = 0;
        openbox(this.popUp);
    },

    cancelPhoto: function () {
        "use strict";
        document.getElementById("avatar").reset();
        openbox(this.popUp);
    }
};

function getBase64Image(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
        document.getElementById("image64").value=reader.result;
        document.getElementById("image").src=reader.result;
    };
    reader.onerror = function (error) {
        console.log('Error: ', error);
    };
};

var phoneService = {
    pos : 0,
    popUp: 'phonePopUp',
    mode: 0,
    MINVALUE : 1000,
    MAXVALUE : 99999999,

    savePhone: function () {
        "use strict";
        var form= document.getElementById("telephone");
        if (!(form.operatorCode.value && form.number.value )) {
            alert("Please, fill required fields");
            return false;
        }
        if (form.countryCode.value > this.MINVALUE  ||  form.number.value > this.MAXVALUE ||
            form.operatorCode.value > this.MINVALUE ){
            alert("Please, check input fields");
            return false;
        }
        openbox(this.popUp);

        var table = document.getElementById("phoneTable");

        var i, row,cell1, cell2, cell3,cell4,cell5,cell6,cell7;

        if (this.mode == 0) {
            i = table.rows.length;
            row = table.insertRow(i);
            cell1 = row.insertCell(0);
            cell2 = row.insertCell(1);
            cell3 = row.insertCell(2);
            cell4 = row.insertCell(3);
            cell5 = row.insertCell(4);
            cell6 = row.insertCell(5);
            cell7 = row.insertCell(6);
        }  else {
            i = this.pos;
            row = table.rows[i];
            cell1 = row.cells[0];
            cell2 = row.cells[1];
            cell3 = row.cells[2];
            cell4 = row.cells[3];
            cell5 = row.cells[4];
            cell6 = row.cells[5];
            cell7 = row.cells[6];
        }

        cell1.innerHTML = "<input type='checkbox'  name='phones'/>";

        var fullPhone = form.countryCode.value +" " +form.operatorCode.value +" "+ form.number.value;

        cell2.innerHTML ="<input type='text' form='form' value='"+ fullPhone +"' readonly/>";

        cell3.innerHTML ="<input type='text' form='form' name='type"+i+"' value='"+form.type.value+"' readonly/>";
        cell4.innerHTML ="<input type='text' form='form' name='comment"+i+"' value='"+form.comment.value+"' readonly/>";
        cell5.innerHTML ="<input type='hidden' form='form' name='countryCode"+i+"' value='"+form.countryCode.value+"' />";
        cell6.innerHTML ="<input type='hidden' form='form' name='operatorCode"+i+"' value='"+form.operatorCode.value+"' />";
        cell7.innerHTML ="<input type='hidden' form='form' name='number"+i+"' value='"+form.number.value+"' />";
        form.reset();
    },

    deletePhone: function () {
        "use strict";
        var table = document.getElementById("phoneTable");
        var checkboxes = document.getElementsByName('phones'), length = checkboxes.length;

        for (var i=length - 1; i>=0; i--) {
            if (checkboxes[i].checked) {
                table.deleteRow(i);
            }
        }
    },

    editPhone: function () {
        "use strict";
        var form= document.getElementById("telephone");
        var table = document.getElementById("phoneTable");
        var checkboxes = document.getElementsByName('phones'), length = checkboxes.length;

        for (var i=0; i<length; i++) {
            if (checkboxes[i].checked) {
                var row = table.rows[i];
                form.countryCode.value = row.cells[4].childNodes[0].value;
                form.operatorCode.value = row.cells[5].childNodes[0].value;
                form.number.value = row.cells[6].childNodes[0].value;
                form.type.value = row.cells[2].childNodes[0].value;
                form.comment.value = row.cells[3].childNodes[0].value;
                this.pos = i;
                this.mode = 1;
                openbox(this.popUp);
                break;
            }
        }

    },

    addPhone: function () {
        "use strict";
        this.mode = 0;
        openbox(this.popUp);
    },

    cancelPhone: function () {
        "use strict";
        document.getElementById("telephone").reset();
        openbox(this.popUp);
    }
};

function encodeFile() {
    var preview = document.getElementById('file64');
    var file    = document.getElementById('file').files[0];
    var reader  = new FileReader();

    reader.onloadend = function () {
        preview.value = reader.result;
    }

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.value = "";
    }
}

var attachService = {
    pos : 0,
    popUp: 'attachPopUp',
    mode: 0,

    saveAttach: function () {
        "use strict";
        var form = document.getElementById("attach");
        if (!form.name.value) {
            alert("Please, fill required fields");
            return false;
        }
        if (!form.file.value) {
            alert("Please, fill required fields");
            return false;
        }
        var file = form.file.files[0];
        if(file != undefined && file.size > 1024* 1024) {
            alert("Too big file! Maximum size of file is 1 MB");
            return false;
        }
        openbox(this.popUp);

        var table = document.getElementById("attachTable");

        var i, row,cell1, cell2, cell3, cell4, cell5;

        if (this.mode == 0) {
            i = table.rows.length;
            row = table.insertRow(i);
            cell1 = row.insertCell(0);
            cell2 = row.insertCell(1);
            cell3 = row.insertCell(2);
            cell4 = row.insertCell(3);
            cell5 = row.insertCell(4);
        }  else {
            i = this.pos;
            row = table.rows[i];
            cell1 = row.cells[0];
            cell2 = row.cells[1];
            cell3 = row.cells[2];
            cell4 = row.cells[3];
            cell5 = row.cells[4];
        }

        function format(date) {
            var d = date.getDate();
            var m = date.getMonth() + 1;
            var y = date.getFullYear();
            return '' + y + '-' + (m<=9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
        }

        var today = new Date();
        var dateString = format(today);

        var res = document.getElementById("file64").value;

        cell1.innerHTML = "<input type='checkbox'  name='attachments'/>";
        cell2.innerHTML ="<a id='file64a' href='"+res+"' target='_blank\'><input type='text' form='form' name='name"+i+"' value='"+form.name.value+"' readonly/></a>";
        cell3.innerHTML ="<input type='text' form='form' name='commentAtt"+i+"' value='"+form.commentAtt.value+"' readonly/>";
        cell4.innerHTML ="<input type='text' form='form' name='date"+i+"' value='"+dateString+"' readonly/>";
        cell5.innerHTML ="<input type='hidden' form='form' name='base64File"+i+"' value='"+res+"' readonly/>";
        form.reset();
    },

    deleteAttach: function () {
        "use strict";
        var table = document.getElementById("attachTable");
        var checkboxes = document.getElementsByName('attachments'), length = checkboxes.length;

        for (var i=length - 1; i>=0; i--) {
            if (checkboxes[i].checked) {
                table.deleteRow(i);
            }
        }
    },

    addAttach: function () {
        "use strict";
        this.mode = 0;
        openbox(this.popUp);
    },

    cancelAttach: function () {
        "use strict";
        document.getElementById("attach").reset();
        openbox(this.popUp);
    }
};