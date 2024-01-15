import pygame
import random

# Inisialisasi Pygame
pygame.init()

# Konstanta
WIDTH, HEIGHT = 800, 600
PLAYER_SIZE = 50
ENEMY_SIZE = 50
PLAYER_SPEED = 5
ENEMY_SPEED = 7
WHITE = (255, 255, 255)
RED = (255, 0, 0)

# Membuat layar
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Avoid the Enemies")

# Membuat pemain
player = pygame.Rect(WIDTH // 2 - PLAYER_SIZE // 2, HEIGHT - 2 * PLAYER_SIZE, PLAYER_SIZE, PLAYER_SIZE)

# Membuat musuh
enemies = []

# Fungsi untuk membuat musuh baru
def create_enemy():
    enemy_size = random.randint(ENEMY_SIZE // 2, ENEMY_SIZE * 2)
    enemy = pygame.Rect(random.randint(0, WIDTH - enemy_size), 0, enemy_size, enemy_size)
    enemies.append(enemy)

# Fungsi untuk menggambar pemain dan musuh
def draw_objects():
    screen.fill(WHITE)
    pygame.draw.rect(screen, RED, player)
    for enemy in enemies:
        pygame.draw.rect(screen, RED, enemy)


# Loop permainan
clock = pygame.time.Clock()
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Menggerakkan pemain
    keys = pygame.key.get_pressed()
    if keys[pygame.K_LEFT] and player.x > 0:
        player.x -= PLAYER_SPEED
    if keys[pygame.K_RIGHT] and player.x < WIDTH - PLAYER_SIZE:
        player.x += PLAYER_SPEED

    # Menggerakkan musuh ke bawah
    for enemy in enemies:
        enemy.y += ENEMY_SPEED
        # Menghapus musuh jika mencapai batas bawah layar
        if enemy.y > HEIGHT:
            enemies.remove(enemy)

    # Membuat musuh baru secara acak
    if random.randint(1, 50) == 1:
        create_enemy()

    # Deteksi tabrakan dengan musuh
    for enemy in enemies:
        if player.colliderect(enemy):
            print("Game Over!")
            running = False

    # Menggambar objek di layar
    draw_objects()

    # Menampilkan layar
    pygame.display.flip()

    # Menetapkan kecepatan frame
    clock.tick(30)

# Keluar dari Pygame
pygame.quit()
