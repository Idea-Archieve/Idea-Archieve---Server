package Idea.Archive.IdeaArchive.global.health;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Void> health() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}