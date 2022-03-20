let cpaPassword1 = document.getElementById("cpaPassword1");
let cpaPassword2 = document.getElementById("cpaPassword2");
let cpaForm = document.getElementById("change_password");
cpaForm.addEventListener("submit", function (event) {
    let password1Value = cpaPassword1.value;
    let password2Value = cpaPassword2.value;

    if (password2Value != password1Value) {
        event.preventDefault();
        cpaPassword2.setCustomValidity("Both passwords must match.");
        cpaPassword2.reportValidity();
    } else {
        cpaPassword2.setCustomValidity("");
    }
});
cpaPassword1.addEventListener("input", function (event) {
    if (cpaPassword1.validity.patternMismatch) {
        cpaPassword1.setCustomValidity("Must be atleast 8 characters, contain 1 uppercase letter, 1 lowercase letter, 1 symbol, and 1 number");
        cpaPassword1.reportValidity();
    } else {
        cpaPassword1.setCustomValidity("");
        cpaPassword1.reportValidity();
    }
});
