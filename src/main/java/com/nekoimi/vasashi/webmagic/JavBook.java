package com.nekoimi.vasashi.webmagic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>JavBook</p>
 *
 * @author nekoimi 2022/4/28
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JavBook implements Serializable {
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String originalUrl;
    private String movieNumber;
    private String actress;
    private String cover;
    private List<String> imageList;
    private List<String> magnetLink;
    private String torrentUrl;
}
