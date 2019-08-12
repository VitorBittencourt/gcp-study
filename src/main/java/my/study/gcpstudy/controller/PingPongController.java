package my.study.gcpstudy.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import my.study.gcpstudy.dto.PingPongDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/gcp-study/ping-pong")
@RequiredArgsConstructor
public class PingPongController {

  @GetMapping("/hello/{str}")
  @SneakyThrows
  public ResponseEntity helloWorld(
    @PathVariable final String str
  ) {
    log.info("Received \"{}\"", str);
    return ResponseEntity.ok("SPAM! " + str);
  }

  @PostMapping("/respond")
  @SneakyThrows
  public ResponseEntity respond(
    @RequestBody @Valid final PingPongDTO pingPongDTO
  ) {
    log.info("\"{}\"", pingPongDTO);
    return ResponseEntity.ok().build();
  }
}
