#include <SFML/Graphics.hpp>
#include <iostream>
#include <vector>

// Fungsi utama
int main() {
    // Konstanta
    const int WIDTH = 800;
    const int HEIGHT = 600;
    const int PLAYER_SIZE = 50;
    const int ENEMY_SIZE = 50;
    const float PLAYER_SPEED = 5.0f;
    const float ENEMY_SPEED = 7.0f;


    sf::RenderWindow window(sf::VideoMode(WIDTH, HEIGHT), "Avoid the Enemies");
buat pemain
    sf::RectangleShape player(sf::Vector2f(PLAYER_SIZE, PLAYER_SIZE));
    player.setPosition(WIDTH / 2 - PLAYER_SIZE / 2, HEIGHT - 2 * PLAYER_SIZE);
    player.setFillColor(sf::Color::Red);

    std::vector<sf::RectangleShape> enemies;
inan
    while (window.isOpen()) {
        // Handle event
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }
        }


        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left) && player.getPosition().x > 0) {
            player.move(-PLAYER_SPEED, 0);
        }
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right) && player.getPosition().x < WIDTH - PLAYER_SIZE) {
            player.move(PLAYER_SPEED, 0);
        }

        for (auto& enemy : enemies) {
            enemy.move(0, ENEMY_SPEED);
            
            if (enemy.getPosition().y > HEIGHT) {
                enemies.erase(enemies.begin());
            }
        }

        if (rand() % 50 == 1) {
            sf::RectangleShape enemy(sf::Vector2f(ENEMY_SIZE, ENEMY_SIZE));
            enemy.setPosition(rand() % (WIDTH - ENEMY_SIZE), 0);
            enemy.setFillColor(sf::Color::Red);
            enemies.push_back(enemy);
        }

        for (auto& enemy : enemies) {
            if (player.getGlobalBounds().intersects(enemy.getGlobalBounds())) {
                std::cout << "Game Over!" << std::endl;
                window.close();
            }
        }

        // Menggambar objek di window
        window.clear(sf::Color::White);
        window.draw(player);
        for (const auto& enemy : enemies) {
            window.draw(enemy);
        }
        window.display();
    }

    return 0;
}
