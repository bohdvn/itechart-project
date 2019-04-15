function val() {
    d = document.getElementById("select_id").value;
    if (d==="Hello") {
        document.getElementById("subject").value = "Hello";
        document.getElementById('subject').setAttribute('readonly', 'readonly');
        document.getElementById("content").innerHTML = "Hello, <names>!";
        document.getElementById('content').setAttribute('readonly', 'readonly');
    } else if (d=="Meeting") {

        document.getElementById("subject").value = "Meeting";
        document.getElementById('subject').setAttribute('readonly', 'readonly');
        document.getElementById("content").innerHTML = "Hello, <names>! Don't forget about tomorrows meeting.";
        document.getElementById('content').setAttribute('readonly', 'readonly');
    }
    else{
        document.getElementById("subject").value = "";
        document.getElementById('subject').removeAttribute( 'readonly');
        document.getElementById("content").innerHTML = "";
        document.getElementById('content').removeAttribute( 'readonly');
    }
}