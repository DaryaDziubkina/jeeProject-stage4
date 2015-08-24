function sendForm(ref) {
    document.noteForm.action.value = ref;
    document.noteForm.submit();
}

function activeClass(element){
    d = document.getElementById(element);
    d.classList.add('active');
}

function setNoteId(id) {
    document.getElementById('noteId').value = id;
}
function activeNote(ref, id){
    setNoteId(id);
    sendForm(ref);

}