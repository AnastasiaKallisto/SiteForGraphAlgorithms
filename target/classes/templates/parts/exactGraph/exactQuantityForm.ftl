<dialog class="littleForm" id="setQuantityForm">
    <form class="form-container"
          action=""
          method="POST">
        <p>Enter the number of graph vertices (from 1 to 999)</p>
        <@spring.formInput "exactQuantityForm.quantity" "" "text"/>

        <div class="buttons-container">
            <button type="button"
                    class="btn-cancel"
                    onclick='closeForm("setQuantityForm")'>Cancel
            </button>
            <input type="submit" class="btn" id="generate" value="Generate"/>
        </div>
    </form>
</dialog>

<script>
    let q = document.getElementById("quantity");
    q.pattern = "[0-9]{1,3}";
    q.setAttribute("onkeyup", "this.value = this.value.replace(/[^\\d]/g,``)");
    q.classList.add('inputQuantity');
    q.setAttribute('required', '');
</script>