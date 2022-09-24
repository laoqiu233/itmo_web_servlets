const canvas = document.getElementById("graph-canvas");
const ctx = canvas.getContext('2d');
const loadingImage = document.getElementById("loading-gif");
const themeColor = [0x83, 0x38, 0xec, 0xaa];
const labels = []
let areasImage = undefined;

canvas.hidden = true;
loadingImage.hidden = false;

fetch("getBitmap")
    .then((resp) => {
        if (resp.ok) {
            return resp.text();
        } else {
            throw Error(resp.statusText);
        }
    })
    .then((bitmapB64) => {
        const bitmapBytes = atob(bitmapB64);
        let bitmap = [];
        
        let s = 0;

        for (let i = 0; i < bitmapBytes.length; i++) {
            for (let j = 0; j < 8; j++) {
                bitmap.push((bitmapBytes.charCodeAt(i) >> (7 - j)) % 2);

                if (bitmap.length >= (s+1) * (s+1)) s++;
            }
        }

        bitmap = bitmap.slice(0, s*s);
        areasImage = generateImageDataFromBitmap(ctx, bitmap, s);

        renderGraph();
        canvas.hidden = false;
        loadingImage.hidden = true;
    })
    .catch((err) => {
        alert('Failed to fetch canvas data');
        console.log(err);
    });

function generateImageDataFromBitmap(ctx, bitmap, bitmapSize) {
    const w = canvas.width;
    const imageData = ctx.createImageData(w, w);

    for (let y = 0; y < w; y++) {
        for (let x = 0; x < w; x++) {
            const x_ = Math.floor(x * bitmapSize / w);
            const y_ = Math.floor(y * bitmapSize / w);

            if (bitmap[y_ * bitmapSize + x_]) {
                themeColor.forEach((v, c) => imageData.data[(y*w+x)*4+c] = v);
            }
        }
    }

    return imageData;
}

function renderGraph() {
    ctx.putImageData(areasImage, 0, 0);

    const width = canvas.width;
    const height = canvas.height;

    ctx.strokeStyle = '#FFF';
    ctx.fillStyle = '#FFF';

    ctx.beginPath();
    ctx.moveTo(0, height/2);
    ctx.lineTo(width, height/2);
    ctx.lineTo(width-10, height/2-10);
    ctx.moveTo(width, height/2);
    ctx.lineTo(width-10,height/2+10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width/2, height);
    ctx.lineTo(width/2, 0);
    ctx.lineTo(width/2-10, 10);
    ctx.moveTo(width/2, 0);
    ctx.lineTo(width/2+10, 10);
    ctx.stroke();

    const labels = ['-R', '-R/2', '', 'R/2', 'R'];

    // Draw axes labels
    for (let i=1; i<6; i++) {
        ctx.beginPath();
        ctx.moveTo(i*width/6, height/2-5);
        ctx.lineTo(i*width/6, height/2+5);
        ctx.moveTo(width/2-5, i*height/6);
        ctx.lineTo(width/2+5, i*height/6);
        ctx.stroke();

        ctx.textAlign = 'center';
        ctx.textBaseline = 'bottom';
        ctx.fillText(labels[i-1], i*width/6, height/2-7);

        ctx.textAlign = 'left';
        ctx.textBaseline = 'middle';
        ctx.fillText(labels[i-1], width/2+7, height - i*height/6);
    }
}