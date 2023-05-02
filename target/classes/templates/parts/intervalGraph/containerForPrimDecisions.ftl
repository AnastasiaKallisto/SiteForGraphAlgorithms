<div class = "container-for-graphs">
  <table id="primDecisionsTable" border="1">
    <tr>
      <th>Decision</th>
      <th>Probability</th>
      <th>Min weight</th>
      <th>Max weight</th>
    </tr>
    <script>
      let primGraphsDto = JSON.parse(${intervalPrimGraphs});
      let primDecisions = primGraphsDto.decisions;

      primDecisions.forEach((decision)=>{
        //создаем новую строку для таблицы
        let newRow = document.createElement("tr");

        let graphCell = document.createElement("td");
        graphCell.addEventListener('click', function() {
          console.log("pushed button prim " + decision);
          fetch('/interval/prim/'+decision.id);
          window.location.reload();
        });
        graphCell.addEventListener("mouseover", function() {
          graphCell.style.backgroundColor = "peachpuff";
        });
        graphCell.addEventListener("mouseout", function() {
          graphCell.style.backgroundColor = "inherit";
        });
        graphCell.textContent = decision.id;

        newRow.appendChild(graphCell);

        let verticesCell = document.createElement("td");
        verticesCell.innerText = "" + decision.probability;
        newRow.appendChild(verticesCell);

        let minWeightCell = document.createElement("td");
        minWeightCell.innerText = "" + decision.minWeight;
        newRow.appendChild(minWeightCell);

        let maxWeightCell = document.createElement("td");
        maxWeightCell.innerText = "" + decision.maxWeight;
        newRow.appendChild(maxWeightCell);

        //добавляем новую строку в конец таблицы
        let table = document.getElementById("primDecisionsTable");
        table.appendChild(newRow);
      })
    </script>
  </table>
</div>