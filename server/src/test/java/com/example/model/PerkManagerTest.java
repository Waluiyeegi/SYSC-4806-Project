package com.example.model;

import com.example.model.Perk;
import com.example.model.PerkManager;
import com.example.repository.PerkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerkManagerTest {

    @Mock
    private PerkRepository perkRepository;

    @InjectMocks
    private PerkManager perkManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPerks() {
        // Given
        List<Perk> mockPerks = Arrays.asList(
                createPerk(1L, "Free video game", "LeagueofLegends", "Product A", "Gold", "Canada"),
                createPerk(2L, "Free Basketball", "BBall", "Product B", "Silver", "China")
        );
        when(perkRepository.findAll()).thenReturn(mockPerks);


        List<Perk> perks = perkManager.getPerks();


        assertNotNull(perks);
        assertEquals(2, perks.size());
        assertEquals("Free video game", perks.get(0).getName());
        assertEquals("Free Basketball", perks.get(1).getName());
        verify(perkRepository, times(1)).findAll();
    }

    @Test
    void testSavePerk() {
        // Given
        Perk mockPerk = createPerk(1L, "Free video game", "LeagueofLegends", "Product A", "Gold", "Canada");
        when(perkRepository.save(mockPerk)).thenReturn(mockPerk);

        // When
        Perk savedPerk = perkManager.savePerk(mockPerk);

        // Then
        assertNotNull(savedPerk);
        assertEquals("Free video game", savedPerk.getName());
        verify(perkRepository, times(1)).save(mockPerk);
    }

    @Test
    void testGetPerkByID_Found() {
        // Given
        Perk mockPerk = createPerk(1L, "Free video game", "LeagueofLegends", "Product A", "Gold", "Canada");
        when(perkRepository.findById(1L)).thenReturn(Optional.of(mockPerk));

        // When
        Perk perk = perkManager.getPerkByID(1L);

        // Then
        assertNotNull(perk);
        assertEquals("Free video game", perk.getName());
        verify(perkRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPerkByID_NotFound() {
        // Given
        when(perkRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> perkManager.getPerkByID(1L));
        assertEquals("com.example.model.Perk[1] not found", exception.getMessage());
        verify(perkRepository, times(1)).findById(1L);
    }

    @Test
    void testDeletePerk() {
        // Given
        Long perkId = 1L;
        doNothing().when(perkRepository).deleteById(perkId);

        // When
        perkManager.deletePerk(perkId);

        // Then
        verify(perkRepository, times(1)).deleteById(perkId);
    }

    // Helper method to just create my perks without me having to do each command every time
    private Perk createPerk(Long id, String name, String code, String product, String membership, String geographicArea) {
        Perk perk = new Perk();
        perk.setId(id);
        perk.setName(name);
        perk.setCode(code);
        perk.setProduct(product);
        perk.setMembership(membership);
        perk.setGeographicArea(geographicArea);
        perk.setExpiryDate(LocalDate.of(2024, 12, 31));
        perk.setUpvotes(0);
        perk.setDownvotes(0);
        return perk;
    }
}
