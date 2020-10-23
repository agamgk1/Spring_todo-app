package com.spring.course.model;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

// adnotacja @MappedSuperclass oznacza ze jest to klasa bazowa ktora pozwoli na modelowanie na bazie danych
// @ Embedable oznaczaze ta klasa jest do osadzenia w innym miejscu
@Embeddable
class Audit {
    // kolumny pomocnicze - nie beda widoczne w JSON
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    //adnotacja spowoduje uruchominie metody przed zapisem do bazy danych
    @PrePersist
    void prePersist() {
        createdOn = LocalDateTime.now();
    }
    // odpowiedzialna za aktualizacje encji
    @PreUpdate
    void preMarge() {
        updatedOn = LocalDateTime.now();
    }


}
