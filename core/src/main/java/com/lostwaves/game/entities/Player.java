package com.lostwaves.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Texture texture;
    private Rectangle bounds;
    private float speed = 250f; // Velocidade de movimento da surfista

    public Player(float startX, float startY) {
        // Carrega o arquivo salvo na pasta assets
        texture = new Texture("sea/surfer.png");
        // Cria a caixa de colisão e posição (x, y, largura, altura)
        bounds = new Rectangle(startX, startY, 100, 100);
    }

    public void update(float delta) {
        // Movimentação com Teclas (WASD ou Setas)
        if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
            bounds.y += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
            bounds.y -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
            bounds.x -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
            bounds.x += speed * delta;
        }

        // Limites da tela (800x480) para não sair do mar
        // Não deixa sair pela esquerda (X = 0)
        if (bounds.x < 0) bounds.x = 0;
        // Não deixa sair pela direita (800 - largura da surfista)
        if (bounds.x > 800 - bounds.width) bounds.x = 800 - bounds.width;
        // Não deixa afundar para fora da tela (Y = 0)
        if (bounds.y < 0) bounds.y = 0;
        if (bounds.y > 180){bounds.y = 180;}
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
