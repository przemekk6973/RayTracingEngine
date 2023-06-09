package rt_Kukla.raytracing.pixeldata;


//bufor pikseli, który przechowuje obrazy w postaci dwuwymiarowej tablicy 
//eprezentuje pojedynczy piksel z jego kolorem, głębokością i emisją

public class PixelBuffer {
    private PixelData[][] pixels;
    private int width, height;

    public PixelBuffer(int width, int height) {
        this.width = width;
        this.height = height;

        this.pixels = new PixelData[width][height];
    }

    public void setPixel(int x, int y, PixelData pixelData) {
        pixels[x][y] = pixelData;
    }

    public PixelData getPixel(int x, int y) {
        return pixels[x][y];
    }


    //filtruje piksele o emisji poniżej wartości minEmission i zamienia je na czarne piksele
    public void filterByEmission(float minEmission) {
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                PixelData pxl = pixels[i][j];
                if (pxl != null && pxl.getEmission() < minEmission) {
                    pixels[i][j] = new PixelData(Color.BLACK, pxl.getDepth(), pxl.getEmission());
                }
            }
        }
    }


    //dodaje do bieżącego bufora inny bufor pikseli, jeśli piksele na danej pozycji w obu buforach są niepuste
    public PixelBuffer add(PixelBuffer other) {
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                PixelData pxl = pixels[i][j];
                PixelData otherPxl = other.pixels[i][j];
                if (pxl != null && otherPxl != null) {
                    //float brightnessB4 = pixels[i][j].getColor().getLuminance();
                    pixels[i][j].add(otherPxl);
                }
            }
        }

        return this;
    }


    //mnoży jasność każdego piksela w buforze przez podaną wartość
    public PixelBuffer multiply(float brightness) {
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                pixels[i][j].multiply(brightness);
            }
        }

        return this;
    }

    // zmienia rozmiar bufora na podaną szerokość i wysokość. 
    //Jeśli parametr linear jest ustawiony na true, metoda dokonuje liniowej interpolacji między pikselami
    //nie zrobione

    public PixelBuffer resize(int newWidth, int newHeight, boolean linear) { 
        PixelBuffer copy = new PixelBuffer(newWidth, newHeight);
        for (int i = 0; i<newWidth; i++) {
            for (int j = 0; j<newHeight; j++) {
                copy.pixels[i][j] = pixels[(int)((float)i/newWidth*width)][(int)((float)j/newHeight*height)];
            }
        }
        return copy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //zlicza ilość pustych pikseli w buforze
    public void countEmptyPixels() {
        int emptyPixels = 0;
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] == null) emptyPixels++;
        }
        System.out.println("Found "+emptyPixels+" empty pixels.");
    }

    @Override
    public PixelBuffer clone() {
        PixelBuffer clone = new PixelBuffer(width, height);
        for (int i = 0; i<pixels.length; i++) {
            System.arraycopy(pixels[i], 0, clone.pixels[i], 0, pixels[i].length);
        }
        return clone;
    }
}
