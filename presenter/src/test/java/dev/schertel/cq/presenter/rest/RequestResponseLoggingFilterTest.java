package dev.schertel.cq.presenter.rest;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import static org.mockito.Mockito.*;

@ExtendWith(RandomBeansExtension.class)
@ExtendWith(MockitoExtension.class)
class RequestResponseLoggingFilterTest {
    @Spy
    Logger logger;

    @Mock
    FilterChain filterChain;

    @InjectMocks
    RequestResponseLoggingFilter cut;

    @BeforeEach
    void setUp() {
        reset(logger, filterChain);
    }

    @Test
    void testLog(@Random HttpMethod httpMethod, @Random URI uri, @Random HttpStatus httpStatus) throws IOException, ServletException {
        // Background
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        doReturn(httpMethod.toString()).when(httpServletRequest).getMethod();
        doReturn(uri.toString()).when(httpServletRequest).getRequestURI();

        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        doReturn(httpStatus.value()).when(httpServletResponse).getStatus();

        // Given

        // When
        cut.doFilter(httpServletRequest, httpServletResponse, filterChain);

        // Then
        verify(logger, times(1)).info(contains("Request"), eq(httpMethod.toString()), eq(uri.toString()));
        verify(logger, times(1)).info(contains("Response"), eq(httpStatus.value()));
    }
}
