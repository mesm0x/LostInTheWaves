package com.lostwaves.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lostwaves.game.entities.Player;

public class LostInTheWaves extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera; //OrthographicCamera serve para criar um mundo estável, em qualquer monitor.
    private Player player;

    // Fundo do Mar
    private Texture oceanTexture;
    private float oceanX1 = 0;
    private float oceanX2;
    private float scrollSpeed = 120f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        oceanTexture = new Texture("sea/sea_day.png");
        oceanX2 = 800;

        // Inicia a surfista na coordenada X=100, Y=200
        player = new Player(100, 120);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // 1. Lógica / Movimentação
        player.update(delta);

        oceanX1 -= scrollSpeed * delta;
        oceanX2 -= scrollSpeed * delta;

        // faz o loop da imagem, se chegar em negativo o oceanx2 com 800 entra.
        if (oceanX1 + 800 <= 0) {
            oceanX1 = oceanX2 + 800; // Se sim, joga ela para trás da Imagem 2
        }
        if (oceanX2 + 800 <= 0) {
            oceanX2 = oceanX1 + 800; // Se sim, joga ela para trás da Imagem 1
        }

        // 2. Renderização
        ScreenUtils.clear(0, 0, 0, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Fundo
        batch.draw(oceanTexture, oceanX1, 0, 800, 480);
        batch.draw(oceanTexture, oceanX2, 0, 800, 480);

        // Personagem
        player.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        oceanTexture.dispose();
        player.dispose();
    }
}
