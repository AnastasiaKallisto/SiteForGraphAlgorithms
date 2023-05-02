function submitForm(formId) {
    const form = document.getElementById(formId);
    fetch(form.action).then(data => {
        location.reload();
    });
}