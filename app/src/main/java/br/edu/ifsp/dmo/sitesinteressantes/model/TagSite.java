package br.edu.ifsp.dmo.sitesinteressantes.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class TagSite {

    private String tag;

    public TagSite(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @NonNull
    @Override
    public String toString() {
        return getTag();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagSite tagSite = (TagSite) o;
        return Objects.equals(tag, tagSite.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
