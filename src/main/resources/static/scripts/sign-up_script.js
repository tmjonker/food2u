let suPassword1 = document.getElementById("suPassword1");
let suPassword2 = document.getElementById("suPassword2");
let signupForm = document.getElementById("sign-up");
signupForm.addEventListener("submit", function (event) {
    let password1Value = suPassword1.value;
    let password2Value = suPassword2.value;

    if (password2Value != password1Value) {
        event.preventDefault();
        suPassword2.setCustomValidity("Both passwords must match.");
        suPassword2.reportValidity();
    } else {
        suPassword2.setCustomValidity("");
    }
});
suPassword1.addEventListener("input", function (event) {
    if (suPassword1.validity.patternMismatch) {
        suPassword1.setCustomValidity("Must be atleast 8 characters, contain 1 uppercase letter, 1 lowercase letter, 1 symbol, and 1 number");
        suPassword1.reportValidity();
    } else {
        suPassword1.setCustomValidity("");
        suPassword1.reportValidity();
    }
});

