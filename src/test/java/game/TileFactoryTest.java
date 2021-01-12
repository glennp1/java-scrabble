package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TileFactoryTest {

    TileFactory tileFactory;

    @BeforeEach
    void setUp() {
        // Set up a new tile factory
        tileFactory = new TileFactory();
    }

    @AfterEach
    void tearDown() {

    }

    /**
     * Check that the correct number of tiles are created
     */
    @Test
    void getNewTilesShouldReturnCorrectNum() {

        LinkedList<Tile> newTiles = tileFactory.getNewTiles();

        assertEquals(TileFactory.NUM_START_TILES, newTiles.size());
    }

    /**
     * Individually check that each tile has a valid character
     */
    void getNewTilesShouldReturnValidChar() {

        LinkedList<Tile> newTiles = tileFactory.getNewTiles();
    }

    /**
     * Individually check that each tile has a valid points value
     */
    void getNewTilesShouldReturnValidPoints() {

    }
}