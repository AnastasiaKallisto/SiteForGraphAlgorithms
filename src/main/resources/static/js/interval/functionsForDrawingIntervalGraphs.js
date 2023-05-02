class IntervalEdge {
    constructor(a, b, minWeight, maxWeight) {
        this.a = a;
        this.b = b;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }
}

class Vertex {
    constructor(x, y, number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
}

class IntervalGraph {

    constructor(probability, id, minWeight, maxWeight) {
        this.id = id;
        this.vertices = [];
        this.edges = [];
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.probability = probability;
    }

    addVertex(vertex) {
        this.vertices.push(vertex);
    }

    addEdge(edge) {
        this.edges.push(edge);
    }
}


function generateVertices(n, sizeFrameX, sizeFrameY) {
    let centerX = sizeFrameX / 2;
    let centerY = sizeFrameY / 2;
    let radius = Math.floor(centerY * 0.9);
    let vertices = [];

    for (let i = 0; i < n; i++) {
        vertices[i] = (new Vertex(
            Math.floor(centerX + radius * Math.cos(i * Math.PI * 2 / n)),
            Math.floor(centerY + radius * Math.sin(i * Math.PI * 2 / n)),
            i+1
        ));
    }
    return vertices;
}

function getProbabilityAndIdOfGraph(graph){
    let answer = "Id: " +
        graph.id +
        ", probability: " +
        graph.probability;
    return answer;
}

function graphToReadingFormat(graph) {
    let answer =
        "Min weight: " +
        graph.minWeight +
        "\nMax weight: " +
        graph.maxWeight +
        "\nEdges:\n";
    graph.edges.forEach((edge) => {
        answer += "(" + edge.a.number +
            ", " + edge.b.number +
            ") [" + edge.minWeight.toFixed(2) +
            " - " +
            edge.maxWeight.toFixed(2) +
            "]\n";
    });
    return answer;
}

function drawIntervalGraph(graph, color) {
    let canvas = document.getElementById('canvasForGraph');
    let ctx = canvas.getContext('2d');
    // граф
    // Ребра
    ctx.strokeStyle = color;
    ctx.lineWidth = 1;
    ctx.font = "12px serif";
    ctx.fillStyle = color;
    graph.edges.forEach(function(edge) {
        ctx.beginPath();
        ctx.moveTo(edge.a.x, edge.a.y);
        ctx.lineTo(edge.b.x, edge.b.y);
        ctx.stroke();
        ctx.fillText( edge.minWeight + "-" + edge.maxWeight,
            Math.round((edge.a.x+edge.b.x)*0.5)-10,
            Math.round((edge.a.y+edge.b.y)*0.5)-10);
    });
    // Вершины
    ctx.fillStyle = color;
    graph.vertices.forEach(function(vertex) {
        ctx.beginPath();
        ctx.arc(vertex.x, vertex.y, 2, 0, 2*Math.PI);
        ctx.fillText("V" + vertex.number, vertex.x+5, vertex.y+15);
        ctx.fill();
    });
}