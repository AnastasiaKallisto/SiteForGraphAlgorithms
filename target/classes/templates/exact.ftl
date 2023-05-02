<#import "/spring.ftl" as spring/>

<#include "parts/common/headerMenu.html">

<#include "parts/exactGraph/exactMenu.html">

<#include "parts/exactGraph/dropdownButtonExact.ftl">

<#include "parts/exactGraph/exactQuantityForm.ftl">

<p id="textExact" class="textInfo">
      <#if exactQuantity!= 0>
            A graph with exact weights, the number of vertices = ${exactQuantity}
      <#else>
            To get started, generate a graph.
      </#if>
</p>

<#include "parts/common/canvasForGraph.html">

<script src="/js/common/script.js"></script>
<script src="/js/exact/functionsForDrawingExactGraphs.js"></script>
<script src="/js/common/scriptSubmitForm.js"></script>

<#if exactQuantity!= 0>
      <script>
          let g = JSON.parse(${exactGraph});
          let n = g.vertices.length;
          let sizeFrameX = 680;
          let sizeFrameY = 680;
          let vertices = generateVertices(n, sizeFrameX, sizeFrameY);
          let graph = new ExactGraph();
          vertices.forEach((vertex) => {
              graph.addVertex(vertex);
          });
          g.edges.forEach((edge) => {
              let e = new ExactEdge(vertices[edge.a - 1], vertices[edge.b - 1], edge.weight);
              graph.addEdge(e);
          });
          drawExactGraph(graph);
          let s = graphToReadingFormat(graph);
          document.getElementById("textGraph").value = s;

          g = JSON.parse(${exactPrimGraph});
          n = g.vertices.length;
          if (n!=0){
              graph = new ExactGraph();
              vertices.forEach((vertex) => {
                  graph.addVertex(vertex);
              });
              g.edges.forEach((edge) => {
                  let e = new ExactEdge(vertices[edge.a - 1], vertices[edge.b - 1], edge.weight);
                  graph.addEdge(e);
              });
              drawPrimTree(graph);
              s = graphToReadingFormat(graph);
              document.getElementById("MSTPrim").value = s;
          }

          g = JSON.parse(${exactKruskalGraph});
          n = g.vertices.length;
          if (n!=0){
              graph = new ExactGraph();
              vertices.forEach((vertex) => {
                  graph.addVertex(vertex);
              });
              g.edges.forEach((edge) => {
                  let e = new ExactEdge(vertices[edge.a - 1], vertices[edge.b - 1], edge.weight);
                  graph.addEdge(e);
              });
              drawKruskalTree(graph);
              s = graphToReadingFormat(graph);
              document.getElementById("MSTCruscal").value = s;
          }
      </script>
<#else>
    <script>
        document.getElementById("textGraph").value = "";
        document.getElementById("MSTPrim").value = "";
        document.getElementById("MSTCruscal").value = "";
    </script>
</#if>
<script src="/js/common/scriptForMenu.js"></script>
</body>

</html>